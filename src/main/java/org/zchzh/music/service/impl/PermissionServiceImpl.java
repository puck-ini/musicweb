package org.zchzh.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.zchzh.music.model.entity.Permission;
import org.zchzh.music.model.entity.RolePermission;
import org.zchzh.music.model.entity.UserRole;
import org.zchzh.music.repository.PermissionRepo;
import org.zchzh.music.repository.RolePermissionRepo;
import org.zchzh.music.repository.UserRoleRepo;
import org.zchzh.music.service.PermissionService;
import org.zchzh.music.types.PermissionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private RolePermissionRepo rolePermissionRepo;

    @Autowired
    private PermissionRepo permissionRepo;


    @Override
    public boolean hasPermission(Long userId, String url) {
        Set<String> permissionUrlSet = buildPermissionUrlSet(getPermission(userId));
        for (String permissionUrl : permissionUrlSet) {
            PathMatcher matcher = new AntPathMatcher();
            return matcher.match(permissionUrl, url);
        }
        return false;
    }

    @Override
    public List<Permission> getPermission(Long userId) {
        List<UserRole> userRoleList = userRoleRepo.findAllByUserRoleId_UserId(userId);
        List<Long> roleIdList = userRoleList.stream()
                .map(item -> item.getUserRoleId().getRoleId())
                .collect(Collectors.toList());
        List<Long> permissionIdList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            List<RolePermission> rolePermissionList = rolePermissionRepo.findAllByRolePermissionId_RoleId(roleId);
            permissionIdList.addAll(rolePermissionList.stream()
                    .map(item -> item.getRolePermissionId().getPermissionId())
                    .collect(Collectors.toList()));
        }
        return permissionRepo.findAllByIdInAndType(permissionIdList, PermissionType.BACK_END);
    }


    private Set<String> buildPermissionUrlSet(List<Permission> permissionList) {
        return permissionList.stream().map(Permission::getUrl).collect(Collectors.toSet());
    }
}
