package com.didiglobal.logi.security.common.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "消息中心信息")
public class MessageVO {

    @ApiModelProperty(value = "消息id", dataType = "Integer", required = false)
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", dataType = "String", required = false)
    private String title;

    /**
     * 内容信息
     */
    @ApiModelProperty(value = "内容信息", dataType = "String", required = false)
    private String content;

    /**
     * 是否已读
     */
    @ApiModelProperty(value = "是否已读", dataType = "Boolean", required = false)
    private Boolean readTag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Long", required = false)
    private Long createTime;

    /**
     * 操作日志id
     */
    @ApiModelProperty(value = "操作日志id", dataType = "Integer", required = false)
    private Integer oplogId;
}
