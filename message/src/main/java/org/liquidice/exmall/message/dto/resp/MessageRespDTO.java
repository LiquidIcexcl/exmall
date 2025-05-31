package org.liquidice.exmall.message.dto.resp;

import lombok.Data;
import org.liquidice.exmall.framework.database.BaseDO;
import org.liquidice.exmall.message.dao.entity.MessageDO;

@Data
public class MessageRespDTO extends BaseDO{

    /**
     * 消息Id
     */
    private Long id; // 消息id唯一凭证

    /**
     * 发送者Id
     */
    private Long senderId; //发起者Id

    /**
     * 接收者Id
     */
    private Long receiverId; //接收者Id

    /**
     * 消息内容
     */
    private String messageContent; //消息内容
}
