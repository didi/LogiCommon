package com.didiglobal.logi.security.common.dto.message;

import lombok.Data;

/**
 * @author cjm
 */
@Data
public class MessageDTO {

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 操作日志id
     */
    private Integer oplogId;

    /**
     * 消息所属用户id
     */
    private Integer userId;

    public MessageDTO(Integer userId, Integer oplogId) {
        this.userId = userId;
        this.oplogId = oplogId;
    }
}
