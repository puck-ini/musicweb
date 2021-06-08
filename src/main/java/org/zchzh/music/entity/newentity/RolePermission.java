package org.zchzh.music.entity.newentity;

import lombok.Data;
import org.zchzh.music.types.RolePermissionId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Data
@Entity
public class RolePermission {
    @EmbeddedId
    private RolePermissionId rolePermissionId;
}
