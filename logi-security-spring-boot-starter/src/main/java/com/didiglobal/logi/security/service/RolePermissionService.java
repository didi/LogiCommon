package com.didiglobal.logi.security.service;

import java.util.List;

/**
 * @author cjm
 */
public interface RolePermissionService {

    /**
     * 保存角色与权限的关联信息
     * @param roleId 角色id
     * @param permissionIdList 权限idList
     */
    void saveRolePermission(Integer roleId, List<Integer> permissionIdList);

    /**
     * 更新角色与权限的关联信息，保存新关系之前会删除old的关联信息
     * @param roleId 角色id
     * @param permissionIdList 权限idList
     */
    void updateRolePermission(Integer roleId, List<Integer> permissionIdList);

    /**
     * 删除角色与权限的关联信息
     * @param roleId 角色id
     */
    void deleteRolePermissionByRoleId(Integer roleId);

    /**
     * 根据角色id，获取权限idList
     * @param roleId 角色id
     * @return 权限idList
     */
    List<Integer> getPermissionIdListByRoleId(Integer roleId);

    /**
     * 根据角色idList，获取权限idList
     * @param roleIdList 角色idList
     * @return 权限idList
     */
    List<Integer> getPermissionIdListByRoleIdList(List<Integer> roleIdList);
}
