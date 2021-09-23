package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_user")
public class UserPO extends BasePO {

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属部门id
     */
    private Integer deptId;
}
