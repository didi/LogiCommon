package com.didiglobal.logi.security.common.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 某角色分配N个用户 活 N角色分配给某用户
 */
@Data
@ApiModel(description = "角色分配信息")
public class RoleAssignDTO {

    /**
     * 角色id或用户id
     */
    @ApiModelProperty(value = "角色id或用户id", dataType = "Integer", required = true)
    private Integer id;

    /**
     * 角色idList 或 用户idList
     */
    @ApiModelProperty(value = "角色idList或用户idList", dataType = "List<Integer>", required = true)
    private List<Integer> idList;

    /**
     * true：N个角色分配给1个用户
     * false：1个角色分配给N个用户
     */
    @ApiModelProperty(value = "true：N个角色分配给1个用户、false：1个角色分配给N个用户", dataType = "Boolean", required = true)
    private Boolean flag;
}
