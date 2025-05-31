package org.liquidice.exmall.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.message.dao.entity.MessageDO;
import org.liquidice.exmall.message.dao.mapper.MessageMapper;
import org.liquidice.exmall.message.service.MessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageService {
}
