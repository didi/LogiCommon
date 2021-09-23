package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 资源权限管理
 * 按用户管理：批量分配用户
 * 按资源管理：批量分配资源
 */
@Data
@ApiModel(description = "资源权限管理，批量分配用户和批量分配资源")
public class BatchAssignDTO {

    @ApiModelProperty(value = "用户idList", dataType = "Integer", required = true)
    private List<Integer> userIdList;

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
     * 资源管理级别：
     * 0（不具备任何权限）
     * 1（默认，查看权限）
     * 2（管理权限）
     */
    @ApiModelProperty(value = "资源管理级别：1（查看权限）、2（管理权限）", dataType = "Integer", required = true)
    private Integer controlLevel;

    /**
     * 分配标记
     * true（按资源管理下的批量分配用户）
     * false（按用户管理下的批量分配资源）
     */
    @ApiModelProperty(value = "分配标记：true（按资源管理下的批量分配用户）、false（按用户管理下的批量分配资源）", dataType = "Boolean", required = true)
    private Boolean assignFlag;
}
