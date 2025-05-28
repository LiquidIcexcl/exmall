package org.liquidice.exmall.cart.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 购物车实体类，继承BaseDO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_cart")
public class CartDO extends BaseDO {

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 购物车ID，作为主键且自增
     */
    private Long cartId;

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
    private Long productCount;

    /**
     * 商品计算价格
     */
    private Long productPrice;
}