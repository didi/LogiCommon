package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 资源权限分配信息，分配用户
 *
 * 这里有三种情况
 * 某项目分配N个用户：则该项目下所有资源类别下的所有具体资源权限分配给N个用户
 * 某资源类别分配给N个用户：则该资源类别下的所有具体资源权限分配给N个用户
 * 某具体资源分配给N个用户：则该具体资源权限分配给N个用户
 *
 */
@Data
@ApiModel(description = "资源权限分配信息，分配用户（某项目，某项目下某资源类别，某项目下某资源类别下某具体资源权限->分配N个用户）")
public class AssignToManyUserDTO {

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = true)
    private Integer projectId;

    @ApiModelProperty(value = "资源类别id（如果为null，则表示该项目下的所有具体资源权限都分配给用户list）", dataType = "Integer", required = false)
    private Integer resourceTypeId;

    @ApiModelProperty(value = "具体资源id（如果为null，则表示该资源类别下的所有具体资源权限都分配给用户list）", dataType = "Integer", required = false)
    private Integer resourceId;

    /**
     * userIdList数组长度可以为0，但是不可为null，表示移除所有old该资源权限与用户的关联信息
     */
    @ApiModelProperty(value = "用户idList（数组长度可以为0，但是不可为null）", dataType = "List<Integer>", required = true)
    private List<Integer> userIdList;

    @ApiModelProperty(value = "排除的用户idList（不删除该用户对资源的权限，用于半选状态的用户）", dataType = "List<Integer>", required = false)
    private List<Integer> excludeUserIdList;

    /**
     * 资源管理级别：
     * 1（查看权限）
     * 2（管理权限）
     */
    @ApiModelProperty(value = "资源管理级别：1（查看权限）、2（管理权限）", dataType = "Integer", required = true)
    private Integer controlLevel;
}
