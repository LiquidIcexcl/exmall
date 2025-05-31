package org.liquidice.exmall.product.dto.resp;

import lombok.Data;

@Data
public class ProductInfoRespDTO {
    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品信息ID
     */
    private Long productInfoId;

    /**
     * 商品信息名称
     */
    private String productInfoName;
}
