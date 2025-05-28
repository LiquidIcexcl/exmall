package org.liquidice.exmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.product.dao.entity.ProductDO;
import org.liquidice.exmall.product.dto.req.ProductReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductRespDTO;

public interface ProductService extends IService<ProductDO> {
    /**
     * 根据商品ID查询商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    ProductRespDTO getProductById(Long productId);

    /**
     * 新增商品参数
     *
     * @param requestParam 商品请求参数
     * @return 新增结果
     */
    Boolean addProduct(ProductReqDTO requestParam);

    /**
     * 根据商品ID更新商品参数
     *
     * @param requestParam 商品请求参数
     * @return 更新结果
     */
    Boolean updateProduct(ProductReqDTO requestParam);

    /**
     * 根据商品ID删除商品参数
     *
     * @param productId 商品ID
     * @return 删除结果
     */
    Boolean deleteProduct(Long productId);
}
