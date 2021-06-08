package org.zchzh.music.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zchzh.music.annotation.ApiLog;
import org.zchzh.music.entity.newentity.LogEntity;
import org.zchzh.music.event.LogEvent;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/5/14
 */

@Aspect
@Component
@Slf4j
@ConditionalOnProperty(prefix = "demo.enable", name = "log", havingValue = "true")
public class LogAspect {


//    private final ThreadLocal<DemoLog> logThreadLocal = new ThreadLocal<>();

    @Autowired
    private ApplicationContext context;

    @Pointcut("@annotation(org.zchzh.music.annotation.ApiLog)")
    public void apiLog() {}

    @Pointcut("@annotation(org.zchzh.music.controller.*)")
    public void controllerAspect() {}


//    @Around(value = "controllerAspect()")
    @Around(value = "apiLog()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String userAgent = request.getHeader("User-Agent");

        LogEntity log = LogEntity.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .description(getMethodInfo(point))
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
                        point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(JSON.toJSONString(result))
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(userAgent)
                .browser(UserAgentUtil.parse(userAgent).getBrowser().toString())
                .os(UserAgentUtil.parse(userAgent).getOs().toString()).build();
        context.publishEvent(new LogEvent(log));
        return result;
    }



    /**
     * 获取操作信息
     * @param joinPoint
     * @return
     */
    public static String getMethodInfo(ProceedingJoinPoint joinPoint){
        // 获取连接点目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取连接点参数
        Object[] args = joinPoint.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        }catch (Exception e){
            log.error("反射获取类失败：{}", e.getMessage());
            return null;
        }
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(ApiLog.class).desc();
                    break;
                }
            }
        }
        return description;
    }

    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private String getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return "";
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return "";
        }
        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return JSON.toJSONString(map);
    }


    private static final String LOCALHOST = "127.0.0.1";

    private static final String COMMA = ",";

    private static final String UNKNOWN = "unknown";

    private static final String X_FORWARDED_FOR = "x-forwarded-for";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * 获取ip地址
     * @param request http request
     * @return 返回id地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(COMMA)) {
            ip = ip.split(",")[0];
        }
        if (LOCALHOST.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }
}
