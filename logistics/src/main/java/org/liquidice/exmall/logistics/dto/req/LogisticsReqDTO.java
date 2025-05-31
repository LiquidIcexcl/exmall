package org.liquidice.exmall.logistics.dto.req;

import lombok.Builder;
import lombok.Data;
import org.liquidice.exmall.framework.database.BaseDO;

@Data
public class LogisticsReqDTO extends BaseDO {
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
