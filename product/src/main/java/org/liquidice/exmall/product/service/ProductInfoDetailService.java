package org.liquidice.exmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.product.dao.entity.ProductInfoDO;
import org.liquidice.exmall.product.dao.entity.ProductInfoDetailDO;
import org.liquidice.exmall.product.dto.req.ProductInfoDetailReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductInfoDetailRespDTO;

import java.util.List;

public interface ProductInfoDetailService extends IService<ProductInfoDetailDO> {
    List<ProductInfoDetailRespDTO> getProductInfoDetailByProductInfoId(Long productInfoId);

    void createProductInfoDetail(ProductInfoDetailReqDTO requestParam);

    void updateProductInfoDetail(ProductInfoDetailReqDTO requestParam);

    void deleteProductInfoDetail(Long productInfoDetailId);
}
