package org.liquidice.exmall.product.dto.req;

import lombok.Data;

@Data
public class ProductIconReqDTO {
    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品图标ID
     */
    private Long productIconId;

    /**
     * 商品图标排序ID
     */
    private Long productIconSortId;

    /**
     * 商品图标URL
     */
    private String productIconUrl;

    /**
     * 是否为视频
     */
    private Boolean isVideo;

    /**
     * 商品图标视频图片URL
     */
    private String productIconVideoImageUrl;
}
