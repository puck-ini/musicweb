package org.zchzh.music.exception;

import org.zchzh.music.enums.ExceptionEnum;

public class CommonException extends RuntimeException{

    private Integer code;

    public CommonException(String msg) {
        super(msg);
        this.code = ExceptionEnum.UNKNOWN_ERROR.getCode();
    }

    public CommonException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
