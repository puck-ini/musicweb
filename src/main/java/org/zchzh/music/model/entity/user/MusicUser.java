package org.zchzh.music.model.entity.user;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;
import org.zchzh.music.types.GenderType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/7
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private GenderType gender;
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
