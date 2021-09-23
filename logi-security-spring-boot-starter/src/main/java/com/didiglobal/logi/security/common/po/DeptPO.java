package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 部门信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_dept")
public class DeptPO extends AppBasePO {

    private Integer id;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 描述
     */
    private String description;

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
}
