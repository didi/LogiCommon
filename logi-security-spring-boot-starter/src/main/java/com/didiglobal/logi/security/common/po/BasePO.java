package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author cjm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasePO extends AppBasePO {

    // 根据数据库类型设置自增，否则只能使用Long类型
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic(value = "0", delval = "1")
    private Boolean isDelete = false;
}
