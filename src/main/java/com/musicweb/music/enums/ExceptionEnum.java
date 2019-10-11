package com.musicweb.music.enums;

public enum ExceptionEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    USERNAME_ERROR(100,"用户名错误或不存在"),
    USERNAME_REPEAT(999,"用户已存在"),
    PASSWORD_ERROR(101,"密码错误"),
    CAPTCHA_ERROR(10,"激活失败"),
    TOKEN_ERROR(11,"Token错误"),
    UNKNOWN_SINGER_TYPE(12,"未知歌手类型"),
    DATA_NULL(13,"数据为空"),
    SONG_LIST_NULL(14,"歌单不存在"),
    SONG_NULL(15,"歌单中歌曲不存在"),
    ATTENTION_USER_NULL(16,"关注的用户不存在"),
    UNKNOWN_TYPE(1111,"未知的类型"),
    LIST_OVERFLOW(5555,"超出List的大小，数据库中数据大于8"),
    DATA_EXIST(666,"数据已存在"),
    DELETE_ERROR(4654,"删除失败"),
    TOKEN_EXPIRE(50014,"Token过期或Token错误"),
    JURISDICTION_ERROR(85263,"无该权限"),
    COOKIE_NULL(321465,"cookie为空"),
    UPDATE_ERROR(60000,"更新失败"),
    INSERT_ERROR(60001,"添加失败"),
    LOGIN_NULL(45666,"未登录"),
    REPEAT_INSERT(77789,"已添加"),
    REPEAT_COLLECT(23121,"已收藏"),
    REPEAT_ATTENTION(11788,"已关注");

    ;


    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
