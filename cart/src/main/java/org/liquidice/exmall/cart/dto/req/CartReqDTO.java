package org.liquidice.exmall.cart.dto.req;

import lombok.Data;

@Data
public class CartReqDTO {
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
     * 商品数量
     */
    private Long productCount;
}
