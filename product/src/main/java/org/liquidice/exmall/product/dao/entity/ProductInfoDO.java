package org.liquidice.exmall.product.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 商品信息类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product_info")
public class ProductInfoDO extends BaseDO {

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