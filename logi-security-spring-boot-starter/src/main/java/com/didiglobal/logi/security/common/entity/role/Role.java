package com.didiglobal.logi.security.common.entity.role;

import com.didiglobal.logi.security.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 角色信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 最后修改人（用户账号）
     */
    private String lastReviser;
}
