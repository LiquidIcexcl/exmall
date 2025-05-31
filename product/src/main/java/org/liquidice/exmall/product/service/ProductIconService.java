package org.liquidice.exmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.product.dao.entity.ProductIconDO;
import org.liquidice.exmall.product.dto.req.ProductIconReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductIconRespDTO;

import java.util.List;

public interface ProductIconService extends IService<ProductIconDO> {
    List<ProductIconRespDTO> getProductIconById(Long productId);

    void createProductIcon(ProductIconReqDTO requestParam);

    void updateProductIcon(ProductIconReqDTO requestParam);

    void deleteProductIcon(Long productIconId);
}
