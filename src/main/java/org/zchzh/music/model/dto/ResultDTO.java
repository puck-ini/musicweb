package org.zchzh.music.model.dto;


import lombok.Data;

@Data
public class ResultDTO<T> {

    private String code;

    private String msg;

    private T data;

    public ResultDTO() {}

    public ResultDTO(T data) {
        this("00000","success", data);
    }

    public ResultDTO(String code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }


    public static <T> ResultDTO<T> success() {
        return new ResultDTO<>(null);
    }

    public static <T> ResultDTO<T> success(T t) {
        return new ResultDTO<>(t);
    }

    public static <T> ResultDTO<T> error(T t) {
        return new ResultDTO<>("99999", "error", t);
    }

    public static <T> ResultDTO<T> error(String code, String msg) {
        return new ResultDTO<>(code, msg, null);
    }
}
