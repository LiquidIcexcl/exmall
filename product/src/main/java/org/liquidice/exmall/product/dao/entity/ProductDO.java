package org.liquidice.exmall.product.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 商品类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class ProductDO extends BaseDO {
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
    private Long productBaseAmount;
}
