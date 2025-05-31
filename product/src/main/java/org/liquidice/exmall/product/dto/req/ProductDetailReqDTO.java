package org.liquidice.exmall.product.dto.req;

import lombok.Data;

@Data
public class ProductDetailReqDTO {
    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品详情ID
     */
    private Long productDetailId;

    /**
     * 商品详情排序ID
     */
    private Long productDetailSortId;

    /**
     * 商品详情URL
     */
    private String productDetailUrl;
}
