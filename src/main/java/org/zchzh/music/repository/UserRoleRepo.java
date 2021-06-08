package org.zchzh.music.repository;

import org.zchzh.music.entity.newentity.UserRole;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface UserRoleRepo extends BaseRepo<UserRole, Long> {

    List<UserRole> findAllByUserRoleId_UserId(Long userId);
}
