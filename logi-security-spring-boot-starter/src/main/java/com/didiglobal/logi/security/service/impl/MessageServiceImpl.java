package com.didiglobal.logi.security.service.impl;

import com.didiglobal.logi.security.common.dto.message.MessageDTO;
import com.didiglobal.logi.security.common.entity.Message;
import com.didiglobal.logi.security.common.vo.message.MessageVO;
import com.didiglobal.logi.security.dao.MessageDao;
import com.didiglobal.logi.security.service.MessageService;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Service("logiSecurityMessageServiceImpl")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void saveMessage(MessageDTO messageDTO) {
        Message message = CopyBeanUtil.copy(messageDTO, Message.class);
        messageDao.insert(message);
    }

    @Override
    public List<MessageVO> getMessageListByUserIdAndReadTag(Integer userId, Boolean readTag) {
        List<Message> messageList = messageDao.selectListByUserIdAndReadTag(userId, readTag);

        List<MessageVO> result = new ArrayList<>();
        for(Message message : messageList) {
            MessageVO messageVO = CopyBeanUtil.copy(message, MessageVO.class);
            messageVO.setCreateTime(message.getCreateTime().getTime());
            result.add(messageVO);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeMessageStatus(List<Integer> messageIdList) {
        if(CollectionUtils.isEmpty(messageIdList)) {
            return;
        }
        List<Message> messageList = messageDao.selectListByMessageIdList(messageIdList);
        for(Message message : messageList) {
            // 反转已读状态
            message.setReadTag(!message.getReadTag());
            messageDao.update(message);
        }
    }

    @Override
    public void saveMessages(List<MessageDTO> messageDTOList) {
        if(CollectionUtils.isEmpty(messageDTOList)) {
            return;
        }
        List<Message> messageList = CopyBeanUtil.copyList(messageDTOList, Message.class);
        messageDao.insertBatch(messageList);
    }
}
