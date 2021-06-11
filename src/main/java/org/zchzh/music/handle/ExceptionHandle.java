package org.zchzh.music.handle;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.zchzh.music.VO.ResultVO;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.utils.ResultVOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResultVO handle(CommonException e){
        if (e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            return ResultVOUtil.error(commonException.getCode(), commonException.getMessage());
        }else{
            logger.error("[系统异常]{}",e);
            return ResultVOUtil.error(ExceptionEnum.UNKNOWN_ERROR.getCode(),ExceptionEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    /**
     * 获取 Validator 校验异常 转换成自定义异常
     * @param e MethodArgumentNotValidException 异常
     * @return 返回 dto
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return new ResultVO(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
