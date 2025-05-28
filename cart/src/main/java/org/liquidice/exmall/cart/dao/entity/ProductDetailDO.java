package org.liquidice.exmall.cart.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 商品详情类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product_detail")
public class ProductDetailDO extends BaseDO {
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