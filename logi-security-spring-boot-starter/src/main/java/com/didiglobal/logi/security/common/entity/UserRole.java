package com.didiglobal.logi.security.common.entity;

import lombok.Data;

/**
 * @author cjm
 *
 * 用户角色关系
 */
@Data
public class UserRole {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

    public UserRole() {}

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
