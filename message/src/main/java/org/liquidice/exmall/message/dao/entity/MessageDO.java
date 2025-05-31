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
}
