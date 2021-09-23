package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 资源权限分配，分配资源
 *
 * N资源权限分配给某用户
 */
@Data
@ApiModel(description = "资源权限分配信息，分配资源（N项目、某项目下N资源类别、某项目下某资源类别下N具体资源权限->分配给某用户）")
public class AssignToOneUserDTO {

    @ApiModelProperty(value = "用户id", dataType = "Integer", required = true)
    private Integer userId;

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = false)
    private Integer projectId;

    @ApiModelProperty(value = "资源类别id（如果为null，则表示该项目下的所有具体资源权限都分配给用户list）", dataType = "Integer", required = false)
    private Integer resourceTypeId;

    @ApiModelProperty(
            value = "projectId == null，resourceTypeId == null，则表示项目idList\n" +
                    "projectId != null，resourceTypeId == null，则表示资源类别idList\n" +
                    "projectId != null，resourceTypeId != null，则表示具体资源idList\n" +
                    "（数组长度可以为0，但是不可为null）",
            dataType = "List<Integer>", required = true)
    private List<Integer> idList;

    /**
     * 排除的idList，对于半选中状态的数据，如果用户不取消或者勾选，则放入此数组
     * projectId为null，则excludeIdList存放projectId
     * projectId不为null，resourceTypeId为null，则excludeIdList存放resourceTypeId
     * 具体资源无半选中状态
     */
    @ApiModelProperty(
            value = "排除的idList，对于半选中状态的数据，如果用户不取消或者勾选，则放入此数组\n" +
                    "projectId == null，resourceTypeId == null，则表示项目idList\n" +
                    "projectId != null，resourceTypeId == null，则表示资源类别idList\n" +
                    "具体资源无半选中状态",
            dataType = "Integer", required = true)
    private List<Integer> excludeIdList;

    /**
     * 资源管理级别：
     * 1（查看权限）
     * 2（管理权限）
     */
    @ApiModelProperty(value = "资源管理级别：1（查看权限）、2（管理权限）", dataType = "Integer", required = true)
    private Integer controlLevel;
}
