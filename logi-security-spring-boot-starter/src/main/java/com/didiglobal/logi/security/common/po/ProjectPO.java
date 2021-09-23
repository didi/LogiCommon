package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 项目信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_project")
public class ProjectPO extends BasePO {

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 运行状态（启动 or 停用）
     */
    private Boolean running;

    /**
     * 所属部门id
     */
    private Integer deptId;
}
