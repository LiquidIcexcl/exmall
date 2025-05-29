package org.liquidice.exmall.cart.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 商品信息细节类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product_info_detail")
public class ProductInfoDetailDO extends BaseDO {
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