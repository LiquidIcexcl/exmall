package org.liquidice.exmall.product.dto.resp;

import lombok.Data;

@Data
public class ProductDetailRespDTO {
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
