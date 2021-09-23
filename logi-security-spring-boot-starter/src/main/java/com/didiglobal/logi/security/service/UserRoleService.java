package com.didiglobal.logi.security.service;

import java.util.List;

/**
 * @author cjm
 */
public interface UserRoleService {

    /**
     * 根据角色id，获取用户idList
     * @param roleId 角色id
     * @return 用户idList
     */
    List<Integer> getUserIdListByRoleId(Integer roleId);

    /**
     * 根据用户id，获取角色idList
     * @param userId 用户id
     * @return 角色idList
     */
    List<Integer> getRoleIdListByUserId(Integer userId);

    /**
     * 根据用户id，更新用户与角色的关联信息
     * @param userId 用户id
     * @param roleIdList 角色idList
     */
    void updateUserRoleByUserId(Integer userId, List<Integer> roleIdList);

    /**
     * 根据角色id，更新用户与角色的关联信息
     * @param roleId 角色id
     * @param userIdList 用户idList
     */
    void updateUserRoleByRoleId(Integer roleId, List<Integer> userIdList);

    /**
     * 根据角色id获取授予用户数
     * @param roleId 角色id
     * @return 角色授予用户数
     */
    int getUserRoleCountByRoleId(Integer roleId);
}
