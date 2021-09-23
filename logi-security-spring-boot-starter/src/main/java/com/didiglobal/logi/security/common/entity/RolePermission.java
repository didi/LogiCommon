package com.didiglobal.logi.security.common.entity;

import lombok.Data;

/**
 * @author cjm
 *
 * 角色权限关系
 */
@Data
public class RolePermission {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;
}
