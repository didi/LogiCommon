package com.didiglobal.logi.security.common.enums.oplog;

import lombok.Getter;

/**
 * @author cjm
 *
 * 操作日志
 *
 * 哪种信息：
 * 1：操作页面
 * 2：操作类型
 * 3：对象分类
 */
@Getter
public enum OplogCode {

    /* 操作类型 */
    OPERATE_PAGE(1, "操作页面"),
    OPERATE_TYPE(2, "操作类型"),
    TARGET_TYPE(3, "对象分类");

    private final Integer type;

    private final String info;

    OplogCode(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

}
