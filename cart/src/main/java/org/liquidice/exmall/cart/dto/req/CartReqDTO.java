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
    private Double productPrice;
}
