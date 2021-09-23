package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 角色权限关系
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_role_permission")
public class RolePermissionPO extends AppBasePO {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;
}
