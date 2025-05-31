package org.liquidice.exmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.product.dao.entity.ProductDetailDO;
import org.liquidice.exmall.product.dao.entity.ProductInfoDetailDO;
import org.liquidice.exmall.product.dto.req.ProductDetailReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductDetailRespDTO;

import java.util.List;

public interface ProductDetailService extends IService<ProductDetailDO> {
    List<ProductDetailRespDTO> getProductDetailById(Long productId);

    void createProductDetail(ProductDetailReqDTO requestParam);

    void updateProductDetail(ProductDetailReqDTO requestParam);

    void deleteProductDetail(Long productDetailId);
}
