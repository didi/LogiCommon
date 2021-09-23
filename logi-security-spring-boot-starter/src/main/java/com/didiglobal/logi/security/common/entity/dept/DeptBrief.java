package com.didiglobal.logi.security.common.entity.dept;

import lombok.Data;

/**
 * @author cjm
 * 部门简要信息
 */
@Data
public class DeptBrief {

    private Integer id;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 是否是叶子部门
     */
    private Boolean leaf;

    /**
     * 部门的层级（parentId为0的层级为1）
     */
    private Integer level;

    /**
     * 父部门id
     */
    private Integer parentId;
}
