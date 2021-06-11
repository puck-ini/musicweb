package org.zchzh.music.service;

import org.zchzh.music.model.entity.Permission;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface PermissionService {

    /**
     * 是否有权限
     * @param userId 用户id
     * @param url
     * @return true表示有权限
     */
    boolean hasPermission(Long userId, String url);

    /**
     * 根据用户id获取所有权限
     * @param userId 用户id
     * @return 返回权限列表
     */
    List<Permission> getPermission(Long userId);
}
