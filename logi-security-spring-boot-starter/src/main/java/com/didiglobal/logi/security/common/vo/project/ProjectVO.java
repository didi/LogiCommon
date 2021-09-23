package com.didiglobal.logi.security.common.vo.project;

import com.didiglobal.logi.security.common.vo.dept.DeptBriefVO;
import com.didiglobal.logi.security.common.vo.user.UserBriefVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "项目信息")
public class ProjectVO {

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "项目code（页面展示叫项目id）", dataType = "String", required = false)
    private String projectCode;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名", dataType = "String", required = false)
    private String projectName;

    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人", dataType = "List<UserBriefVO>", required = false)
    private List<UserBriefVO> userList;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "String", required = false)
    private String description;

    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态", dataType = "Boolean", required = false)
    private Boolean running;

    /**
     * 使用部门的信息（包括父亲、祖父部门信息）
     */
    @ApiModelProperty(value = "部门信息（数组，父->子（下标0~len））", dataType = "List<DeptBriefVO>", required = false)
    private List<DeptBriefVO> deptList;

    /**
     * 使用部门id
     */
    @ApiModelProperty(value = "使用部门id（子）", dataType = "Integer", required = false)
    private Integer deptId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间（时间戳ms）", dataType = "Long", required = false)
    private Long createTime;
}
