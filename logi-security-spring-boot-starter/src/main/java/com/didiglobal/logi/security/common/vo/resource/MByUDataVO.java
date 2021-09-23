package com.didiglobal.logi.security.common.vo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "按用户管理/分配资源/数据列表")
public class MByUDataVO {

    @ApiModelProperty(value = "数据id（项目id、资源类别id、资源id）", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "数据名（项目名、资源类别名、资源名）", dataType = "String", required = false)
    private String name;

    @ApiModelProperty(value = "拥有级别（0 不拥有、1 半拥有、2 全拥有）", dataType = "Integer", required = false)
    private Integer hasLevel;

    public MByUDataVO() {}

    public MByUDataVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
