package com.didiglobal.logi.security.common.dto.oplog;

import lombok.Data;

/**
 * @author cjm
 */
@Data
public class OplogDTO {

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
     * 操作日志详情
     */
    private String detail;

    public OplogDTO() {}

    public OplogDTO(String operatePage, String operateType, String targetType, String target, String detail) {
        this.operatePage = operatePage;
        this.operateType = operateType;
        this.targetType = targetType;
        this.target = target;
        this.detail = detail;
    }

    public OplogDTO(String operatePage, String operateType, String targetType, String target) {
        this.operatePage = operatePage;
        this.operateType = operateType;
        this.targetType = targetType;
        this.target = target;
    }
}
