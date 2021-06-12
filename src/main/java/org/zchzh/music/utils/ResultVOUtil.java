package org.zchzh.music.utils;

import org.zchzh.music.model.dto.ResultDTO;

public class ResultVOUtil {

    public static ResultDTO success(Object object){
        ResultDTO resultDTO = new ResultDTO();
//        resultDTO.setCode(0);
        resultDTO.setMsg("成功");
        resultDTO.setData(object);
        return resultDTO;
    }
    public static ResultDTO success(){
        return ResultVOUtil.success(null);
    }

    public static ResultDTO success(Integer code, String msg, Object object){
        ResultDTO resultDTO = new ResultDTO();
//        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        resultDTO.setData(object);
        return resultDTO;
    }

    public static ResultDTO error(Integer code, String msg){
        ResultDTO resultDTO = new ResultDTO();
//        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }
}
