package org.liquidice.exmall.logistics.dao.entity;

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
@TableName("t_logistics")
public class LogisticsDO extends BaseDO {
    /**
     * 物流信息ID
     */
    private Long id;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 接收者ID
     */
    private Long receiverId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 物流方式
     */
    private String logisticsMethod;

    /**
     * 物流状态
     */
    private Integer isFinish; //0:未完成 1:完成
}
