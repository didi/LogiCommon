package com.didiglobal.logi.security.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 操作日志信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Oplog extends BaseEntity {

    /**
     * 操作者ip
     */
    private String operatorIp;

    /**
     * 操作者用户账号
     */
    private String operatorUsername;

    /**
     * 操作页面
     */
    private String operatePage;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 对象分类
     */
    private String targetType;

    /**
     * 操作对象
     */
    private String target;

    /**
     * 详情
     */
    private String detail;
}
