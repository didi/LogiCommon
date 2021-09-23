package com.didiglobal.logi.security.common.entity.dept;

import lombok.Data;

/**
 * @author cjm
 *
 * 部门信息
 */
@Data
public class Dept {

    private Integer id;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 父部门id（根部门parentId为0）
     */
    private Integer parentId;

    /**
     * 是否是叶子部门
     */
    private Boolean leaf;

    /**
     * 部门的层级（parentId为0的层级为1）
     */
    private Integer level;

    /**
     * 描述
     */
    private String description;
}
