package com.common.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@TableName(value = "logi_project_resource")
public class ProjectResourcePO {

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 资源类别id
     */
    private Integer resourceTypeId;

    /**
     * 具体资源id
     */
    private Integer resourceId;

    /**
     * 具体资源名
     */
    private String resourceName;

    /**
     * 应用名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String appName;
}
