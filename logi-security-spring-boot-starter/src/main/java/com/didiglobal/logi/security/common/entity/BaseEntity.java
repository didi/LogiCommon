package com.didiglobal.logi.security.common.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author cjm
 */
@Data
public class BaseEntity {

    private Integer id;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    private Boolean isDelete = false;
}
