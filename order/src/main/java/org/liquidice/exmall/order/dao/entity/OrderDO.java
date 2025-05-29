package org.liquidice.exmall.order.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 订单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_order")
public class OrderDO extends BaseDO {
    /**
     * 订单表自增主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 订单ID（业务层面标识订单的编号）(可同名)
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品细节码
     */
    private String productSkuCode = "";

    /**
     * 商品数量
     */
    private Long productCount = 0L;

    /**
     * 订单状态：0:待支付 | 1:已完成 | 2:售后处理 | 3:已退款
     */
    private Integer orderState = 0;

    /**
     * 订单子单价
     */
    private Double totalPrice = 0D;
}
