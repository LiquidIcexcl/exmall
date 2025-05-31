package org.liquidice.exmall.product.dto.resp;

import lombok.Data;

@Data
public class ProductInfoDetailRespDTO {
    /**
     * 商品信息ID
     */
    private Long productInfoId;

    /**
     * 商品信息详情ID
     */
    private Long productInfoDetailId;

    /**
     * 商品信息详情名称
     */
    private String productInfoDetailName;

    /**
     * 商品详情金额
     */
    private Double productDetailAmount;
}
