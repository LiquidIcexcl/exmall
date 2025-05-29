package org.liquidice.exmall.product.dto.req;

import lombok.Data;

/**
 * 商品请求数据传输对象
 * 用于接收前端传递的商品相关请求参数
 */
@Data
public class ProductReqDTO {
    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品组名称
     */
    private String productGroupName;

    /**
     * 商品基础金额
     */
    private Double productBaseAmount;
}
