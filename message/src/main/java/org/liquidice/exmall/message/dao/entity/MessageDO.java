package org.liquidice.exmall.message.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_message")
public class MessageDO extends BaseDO {
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
