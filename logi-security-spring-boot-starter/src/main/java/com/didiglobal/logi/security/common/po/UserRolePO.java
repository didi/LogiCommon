package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 用户角色关系
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_user_role")
public class UserRolePO extends AppBasePO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

    public UserRolePO() {}

    public UserRolePO(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
