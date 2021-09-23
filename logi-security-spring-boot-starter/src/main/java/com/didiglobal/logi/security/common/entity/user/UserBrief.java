package com.didiglobal.logi.security.common.entity.user;

import lombok.Data;

/**
 * @author cjm
 * 用户简要信息
 */
@Data
public class UserBrief {

    private Integer id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 所属部门id
     */
    private Integer deptId;
}
