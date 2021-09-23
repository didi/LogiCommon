package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author cjm
 */
@Data
public class AppBasePO {

    /**
     * 应用名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String appName;
}
