package com.didiglobal.logi.security.common.vo.resource;

import com.didiglobal.logi.security.common.vo.dept.DeptBriefVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 资源权限管理>按用户管理的列表信息
 *
 * MByU（ManagerByUser）
 */
@Data
@ApiModel(description = "资源权限管理（按用户管理的列表信息）")
public class MByUVO {

    @ApiModelProperty(value = "用户id", dataType = "Integer", required = false)
    private Integer userId;

    @ApiModelProperty(value = "用户账号", dataType = "String", required = false)
    private String username;

    @ApiModelProperty(value = "真实姓名", dataType = "String", required = false)
    private String realName;

    @ApiModelProperty(value = "部门信息", dataType = "List<DeptBriefVO>", required = false)
    private List<DeptBriefVO> deptList;

    @ApiModelProperty(value = "管理权限资源数", dataType = "Integer", required = false)
    private Integer adminResourceCnt;

    @ApiModelProperty(value = "查看权限资源数", dataType = "Integer", required = false)
    private Integer viewResourceCnt;
}
