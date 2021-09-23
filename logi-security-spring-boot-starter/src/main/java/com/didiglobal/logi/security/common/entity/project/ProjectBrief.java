package com.didiglobal.logi.security.common.entity.project;

import lombok.Data;

/**
 * @author cjm
 * 项目简要信息
 */
@Data
public class ProjectBrief {

    private Integer id;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目编号
     */
    private String projectCode;
}
