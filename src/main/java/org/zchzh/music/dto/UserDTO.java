package org.zchzh.music.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/10
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private Long id;

    private String token;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 邮件地址
     */
    private String mail;
    /**
     * 所在地址
     */
    private String address;
    /**
     * 电话号
     */
    private String phone;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    /**
     * 个人描述
     */
    private String description;
    /**
     * 头像地址
     */
    private String avatar;
}
