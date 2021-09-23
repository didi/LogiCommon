package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 *
 * 具体资源信息
 */
@Data
@ApiModel(description = "按用户管理/分配资源/数据列表的查询条件")
public class MByUDataQueryDTO {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", dataType = "Integer", required = true)
    private Integer userId;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id（2，3展示级别不可为null）", dataType = "Integer", required = false)
    private Integer projectId;

    /**
     * 资源类别id
     */
    @ApiModelProperty(value = "资源类别id（3展示级别不可为null）", dataType = "Integer", required = false)
    private Integer resourceTypeId;

    /**
     * 按资源管理列表展示级别
     */
    @ApiModelProperty(
            value = "按资源管理列表展示级别：1 项目展示级别、2 资源类别展示级别、3 具体资源展示级别",
            dataType = "Integer", required = true)
    private Integer showLevel;

    @ApiModelProperty(value = "资源管理级别：1（查看权限）、2（管理权限）", dataType = "Integer", required = true)
    private Integer controlLevel;

    @ApiModelProperty(value = "是否是批量操作，是否是页面点击批量操作跳转的", dataType = "Boolean", required = true)
    private Boolean batch;
}
