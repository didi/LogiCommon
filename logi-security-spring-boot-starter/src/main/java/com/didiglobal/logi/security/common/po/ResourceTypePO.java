package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 资源类型信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_resource_type")
public class ResourceTypePO extends AppBasePO {

    // 根据数据库类型设置自增，否则只能使用Long类型
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 资源类型名
     */
    private String typeName;
}
