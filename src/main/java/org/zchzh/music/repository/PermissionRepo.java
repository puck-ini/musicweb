package org.zchzh.music.repository;

import org.zchzh.music.model.entity.Permission;
import org.zchzh.music.types.PermissionType;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface PermissionRepo extends BaseRepo<Permission, Long>{
    List<Permission> findAllByIdInAndType(List<Long> ids, PermissionType type);
}
