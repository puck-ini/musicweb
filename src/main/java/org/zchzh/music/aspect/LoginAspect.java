package org.zchzh.music.aspect;


import org.zchzh.music.entity.UserTb;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.enums.UserJurisdictionEnum;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.service.impl.UserTbServiceImpl;
import org.zchzh.music.utils.CookieUtil;
import org.zchzh.music.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Pointcut("execution(public * org.zchzh.music.controller.UserFunctionController.*(..))")
    public void verifyToken(){}


    @Before("verifyToken()")
    public void doVerifyToken(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

       checkCookieAndToken(request);
    }
    @Before("execution(public * org.zchzh.music.controller.AdminFunctionController.*(..))")
    public void adminCheck(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Claims claims = checkCookieAndToken(request);
        UserTb userTb = userTbService.findById(Integer.valueOf(claims.getId()));
        Integer jurisdiction = userTb.getJurisdiction();
        if (!(jurisdiction.equals( UserJurisdictionEnum.ADMIN.getCode()) ||jurisdiction.equals(UserJurisdictionEnum.SINGER.getCode())) ){
            throw new CommonException(ExceptionEnum.JURISDICTION_ERROR);
        }
    }

    private Claims checkCookieAndToken(HttpServletRequest request){
        //查询cookie
        Cookie cookie = CookieUtil.get(request,"Token");
        if (cookie == null){
            throw new CommonException(ExceptionEnum.COOKIE_NULL);
        }
        Claims claims = null;
        try {
            claims = TokenUtil.parseToken(cookie.getValue());
        }catch (Exception e){
            throw new CommonException(ExceptionEnum.TOKEN_EXPIRE);
        }

        if (!Integer.valueOf(claims.getId()).equals(userTbService.findByUsername(claims.getSubject()).getUserId())){
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }
        return claims;
    }

}
