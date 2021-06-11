package org.zchzh.music.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/11
 */
@Data
public class LoginReq implements Serializable {

    @NotNull
    private String loginName;

    @NotNull
    private String password;
}
