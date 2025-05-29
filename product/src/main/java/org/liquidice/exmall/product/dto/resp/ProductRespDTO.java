package org.liquidice.exmall.product.dto.resp;

import lombok.Data;

/**
 * 商品响应数据传输对象
 */
@Data
public class ProductRespDTO {
    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 店铺ID
     */
    private Long shopId;

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
