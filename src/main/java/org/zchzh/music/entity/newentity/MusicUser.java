package org.zchzh.music.entity.newentity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/7
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MusicUser extends BaseEntity {

    /**
     * 用户名称
     */
    private String name;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
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
