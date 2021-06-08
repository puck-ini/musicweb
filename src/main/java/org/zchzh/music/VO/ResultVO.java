package org.zchzh.music.VO;

public class ResultVO<T> {

    private Integer code;

    private String desc;

    private T data;

    public ResultVO(T t) {
        this.data = t;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
