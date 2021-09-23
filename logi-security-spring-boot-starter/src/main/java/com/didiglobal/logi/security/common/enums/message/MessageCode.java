package com.didiglobal.logi.security.common.enums.message;

import lombok.Getter;

/**
 * @author cjm
 */
@Getter
public enum MessageCode {

    /* 角色添加消息 */
    ROLE_ADD_MESSAGE("角色权限变动通知", "管理员于%s，为您分配了角色{%s}，请知悉；"),

    /* 角色移除消息 */
    ROLE_REMOVE_MESSAGE("角色权限变动通知", "管理员于%s，移除了角色{%s}，请知悉；");

    private final String title;

    private final String content;

    MessageCode(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
