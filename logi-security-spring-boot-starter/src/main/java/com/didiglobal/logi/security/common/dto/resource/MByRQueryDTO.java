package com.didiglobal.logi.security.common.dto.resource;

import com.didiglobal.logi.security.common.dto.PageParamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 资源权限管理/按资源管理的列表查询条件
 *
 * MByR（ManageByResource）
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "资源权限管理（按资源管理的列表查询条件）")
public class MByRQueryDTO extends PageParamDTO {

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

    /**
     * 如果projectId为null，则name表示项目名称
     * 如果projectId不为null，resourceTypeId为null，则name表示资源类别名称
     * 如果projectId不为null，resourceTypeId不为null，则name表示具体资源名称
     */
    @ApiModelProperty(
            value =
                    "项目展示级别，则name表示项目名称、" +
                            "资源类别展示级别，则name表示资源类别名称、" +
                            "具体资源展示级别，则name表示具体资源名称）",
            dataType = "Integer", required = false
    )
    private String name;
}
