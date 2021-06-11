package org.zchzh.music.repository;

import org.zchzh.music.model.entity.RolePermission;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface RolePermissionRepo extends BaseRepo<RolePermission, Long> {

    List<RolePermission> findAllByRolePermissionId_RoleId(Long roleId);

}
