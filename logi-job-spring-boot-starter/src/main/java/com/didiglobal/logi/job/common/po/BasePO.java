package com.didiglobal.logi.job.common.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BasePO {

    /**
     * 创建时间
     */
    protected Timestamp createTime;

    /**
     * 更新时间
     */
    protected Timestamp updateTime;
}
