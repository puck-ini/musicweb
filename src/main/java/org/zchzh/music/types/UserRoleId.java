package org.zchzh.music.types;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Embeddable
@Data
public class UserRoleId implements Serializable {

    private Long userId;

    private Long roleId;
}
