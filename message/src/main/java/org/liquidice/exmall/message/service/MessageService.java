package org.liquidice.exmall.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.message.dao.entity.MessageDO;
import org.liquidice.exmall.message.dto.req.MessageReqDTO;
import org.liquidice.exmall.message.dto.resp.MessageRespDTO;

import java.util.List;

public interface MessageService extends IService<MessageDO> {
    /**
     * 获取消息详情
     *
     * @param uid 消息ID
     * @return 消息详情
     */
    List<MessageRespDTO> getMessageById(Long uid);

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    void sendMessage(MessageReqDTO message);

    /**
     * 删除消息
     *
     * @param id 消息ID
     */
    void deleteMessage(Long id);
}
