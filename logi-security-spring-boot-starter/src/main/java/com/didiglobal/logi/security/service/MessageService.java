package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.dto.message.MessageDTO;
import com.didiglobal.logi.security.common.vo.message.MessageVO;

import java.util.List;

/**
 * @author cjm
 */
public interface MessageService {


    /**
     * 保存消息
     * @param messageDTO 消息内容
     */
    void saveMessage(MessageDTO messageDTO);

    /**
     * 根据消息状态，获取消息List
     * @param userId 用户id，如果为null，则获取全部消息
     * @param readTag true已读、false未读、null则全部读
     * @return 消息List
     */
    List<MessageVO> getMessageListByUserIdAndReadTag(Integer userId, Boolean readTag);

    /**
     * 更改消息状态，旧状态取反
     * @param messageIdList 消息idList
     */
    void changeMessageStatus(List<Integer> messageIdList);

    /**
     * 批量保存消息
     * @param messageDTOList 消息List
     */
    void saveMessages(List<MessageDTO> messageDTOList);
}
