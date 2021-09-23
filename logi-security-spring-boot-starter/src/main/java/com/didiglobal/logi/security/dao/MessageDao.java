package com.didiglobal.logi.security.dao;

import com.didiglobal.logi.security.common.entity.Message;

import java.util.List;

/**
 * @author cjm
 */
public interface MessageDao {

    /**
     * 新增消息
     * @param message 消息
     */
    void insert(Message message);

    /**
     * 更新消息
     * @param message 消息体
     */
    void update(Message message);

    /**
     * 批量插入新消息
     * @param messageList 消息List
     */
    void insertBatch(List<Message> messageList);

    /**
     * 根据用户id和已读状态获取消息
     * @param userId 用户id
     * @param readTag 是否已读
     * @return 消息List
     */
    List<Message> selectListByUserIdAndReadTag(Integer userId, Boolean readTag);

    /**
     * 根据消息idList获取
     * @param messageIdList 消息idList
     * @return 消息List
     */
    List<Message> selectListByMessageIdList(List<Integer> messageIdList);


}
