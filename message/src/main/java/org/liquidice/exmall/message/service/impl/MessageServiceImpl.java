package org.liquidice.exmall.message.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.message.dao.entity.MessageDO;
import org.liquidice.exmall.message.dao.mapper.MessageMapper;
import org.liquidice.exmall.message.dto.req.MessageReqDTO;
import org.liquidice.exmall.message.dto.resp.MessageRespDTO;
import org.liquidice.exmall.message.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageService {
    @Override
    public List<MessageRespDTO> getMessageById(Long uid) {
        LambdaQueryWrapper<MessageDO> senderQueryWrapper = Wrappers.lambdaQuery(MessageDO.class)
                .eq(MessageDO::getSenderId, uid)
                .eq(MessageDO::getDelFlag, 0);
        LambdaQueryWrapper<MessageDO> receiverQueryWrapper = Wrappers.lambdaQuery(MessageDO.class)
                .eq(MessageDO::getReceiverId, uid)
                .eq(MessageDO::getDelFlag, 0);
        List<MessageDO> senderMessages = baseMapper.selectList(senderQueryWrapper);
        List<MessageDO> receiverMessages = baseMapper.selectList(receiverQueryWrapper);
        List<MessageRespDTO> senderMessageRespDTOList = BeanUtil.copyToList(senderMessages, MessageRespDTO.class);
        List<MessageRespDTO> receiverMessageRespDTOList = BeanUtil.copyToList(receiverMessages, MessageRespDTO.class);
        List<MessageRespDTO> result = new ArrayList<>(senderMessageRespDTOList);
        result.addAll(receiverMessageRespDTOList);
        if (CollUtil.isEmpty(result)) {
            return new ArrayList<>();
        }
        result.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return result;
    }

    @Override
    public void sendMessage(MessageReqDTO message) {
        MessageDO messageDO = new MessageDO();
        BeanUtil.copyProperties(message, messageDO);
        messageDO.setDelFlag(0); // 默认未删除
        baseMapper.insert(messageDO);
    }

    @Override
    public void deleteMessage(Long id) {
        LambdaUpdateWrapper<MessageDO> updateWrapper = Wrappers.lambdaUpdate(MessageDO.class)
                .eq(MessageDO::getId, id)
                .eq(MessageDO::getDelFlag, 0);
        MessageDO messageDO = new MessageDO();
        messageDO.setDelFlag(1); // 设置为已删除
        boolean updated = baseMapper.update(messageDO, updateWrapper) > 0;
        if (!updated) {
            throw new ServiceException("找不到要删除的消息");
        }
    }
}
