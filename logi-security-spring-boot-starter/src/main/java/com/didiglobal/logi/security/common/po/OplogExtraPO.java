package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 操作日志信息（操作页面、操作类型、对象分类）
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_oplog_extra")
public class OplogExtraPO extends AppBasePO {

    // 根据数据库类型设置自增，否则只能使用Long类型
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作页面、操作类型、对象分类的信息
     */
    private String info;

    /**
     * 哪种信息：
     * 1：操作页面
     * 2：操作类型
     * 3：对象分类
     */
    private Integer type;
}
