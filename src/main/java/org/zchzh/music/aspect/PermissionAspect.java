package org.zchzh.music.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.service.PermissionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Aspect
@Component
@ConditionalOnProperty(prefix = "permission", name = "enable", havingValue = "true")
public class PermissionAspect {

    @Autowired
    private PermissionService permissionService;

    @Pointcut("@annotation(org.zchzh.music.controller.*)")
    public void permissionPointcut() {}


    /**
     * TODO 权限控制
     */
    @Before(value = "permissionPointcut()")
    public void checkPermission() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        if (!permissionService.hasPermission(1L, url)) {
            throw new CommonException("无权限访问");
        }

    }
}
