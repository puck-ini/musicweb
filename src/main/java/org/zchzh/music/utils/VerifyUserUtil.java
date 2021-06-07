package org.zchzh.music.utils;

import org.zchzh.music.entity.UserTb;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.exception.CommonException;

public class VerifyUserUtil {

    public static void verifyUser(UserTb userTb,String password){
        if (userTb == null){
            throw new CommonException(ExceptionEnum.USERNAME_ERROR);
        }
        if (!userTb.getPassword().equals(password)){
            throw new CommonException(ExceptionEnum.PASSWORD_ERROR);
        }
    }
    public static void verifyCaptcha(UserTb userTb){
        if (userTb == null){
            throw new CommonException(ExceptionEnum.CAPTCHA_ERROR);
        }else{
            userTb.setCaptcha(null);
        }
    }

    public static void verifyToken(UserTb userTb){
        if(userTb == null){
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }
    }

    public static void verifyNotNull(UserTb userTb){
        if(userTb == null){
            throw new CommonException(ExceptionEnum.DATA_NULL);
        }
    }
}
