package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 *
 */
@Data
@ApiModel(description = "按资源管理/分配用户/数据列表的查询条件")
public class MByRDataQueryDTO {

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = true)
    private Integer projectId;

    @ApiModelProperty(value = "资源类别id", dataType = "Integer", required = false)
    private Integer resourceTypeId;

    @ApiModelProperty(value = "具体资源id", dataType = "Integer", required = false)
    private Integer resourceId;

    /**
     * 资源管理级别：
     * 1（查看权限）
     * 2（管理权限）
     */
    @ApiModelProperty(value = "资源管理级别：1（查看权限）、2（管理权限）", dataType = "Integer", required = true)
    private Integer controlLevel;

    @ApiModelProperty(value = "是否是批量操作，是否是页面点击批量操作跳转的", dataType = "Boolean", required = true)
    private Boolean batch;
}
