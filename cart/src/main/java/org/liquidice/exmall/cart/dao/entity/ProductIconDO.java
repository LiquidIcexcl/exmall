package org.liquidice.exmall.cart.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 商品Icon类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product_icon")
public class ProductIconDO extends BaseDO {
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