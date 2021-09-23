package com.didiglobal.logi.security.common.vo.user;

import java.util.List;

import com.didiglobal.logi.security.common.vo.dept.DeptBriefVO;
import com.didiglobal.logi.security.common.vo.permission.PermissionTreeVO;
import com.didiglobal.logi.security.common.vo.role.RoleBriefVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "用户信息")
public class UserVO {

    @ApiModelProperty(value = "用户id", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "用户账号", dataType = "String", required = false)
    private String username;

    @ApiModelProperty(value = "真实姓名", dataType = "String", required = false)
    private String realName;

    @ApiModelProperty(value = "电话", dataType = "String", required = false)
    private String phone;

    @ApiModelProperty(value = "邮箱", dataType = "String", required = false)
    private String email;

    @ApiModelProperty(value = "更新时间（时间戳ms）", dataType = "Long", required = false)
    private Long updateTime;

    @ApiModelProperty(value = "部门信息（数组，父->子（下标0~len））", dataType = "List<DeptBriefVO>", required = false)
    private List<DeptBriefVO> deptList;

    @ApiModelProperty(value = "角色信息", dataType = "List<RoleBriefVO>", required = false)
    private List<RoleBriefVO> roleList;

    @ApiModelProperty(value = "权限信息（树）", dataType = "PermissionTreeVO", required = false)
    private PermissionTreeVO permissionTreeVO;
}
