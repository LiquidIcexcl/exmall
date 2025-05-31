package org.liquidice.exmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.product.dao.entity.ProductInfoDO;
import org.liquidice.exmall.product.dto.req.ProductInfoReqDTO;

import java.util.List;

public interface ProductInfoService extends IService<ProductInfoDO> {
    List<ProductInfoReqDTO> getProductInfoById(Long productId);

    void createProductInfo(ProductInfoReqDTO requestParam);

    void updateProductInfo(ProductInfoReqDTO requestParam);

    void deleteProductInfo(Long productInfoId);
}
