package org.zchzh.music.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/11
 */
@Data
public class RegisterReq implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String loginName;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String mail;

}
