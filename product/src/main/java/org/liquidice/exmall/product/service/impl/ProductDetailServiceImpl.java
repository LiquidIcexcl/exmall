package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liquidice.exmall.product.dao.entity.ProductDetailDO;
import org.liquidice.exmall.product.dao.mapper.ProductDetailMapper;
import org.liquidice.exmall.product.dto.req.ProductDetailReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductDetailRespDTO;
import org.liquidice.exmall.product.service.ProductDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetailDO> implements ProductDetailService {


    @Override
    public List<ProductDetailRespDTO> getProductDetailById(Long productId) {
        LambdaQueryWrapper<ProductDetailDO> wrapper = Wrappers.lambdaQuery(ProductDetailDO.class)
                .eq(ProductDetailDO::getProductId, productId)
                .eq(ProductDetailDO::getDelFlag, 0);
        List<ProductDetailDO> productDetailList = baseMapper.selectList(wrapper);
        return BeanUtil.copyToList(productDetailList, ProductDetailRespDTO.class);
    }

    @Override
    public void createProductDetail(ProductDetailReqDTO requestParam) {
        ProductDetailDO productDetailDO = BeanUtil.copyProperties(requestParam, ProductDetailDO.class);
        boolean saveResult = baseMapper.insert(productDetailDO) > 0;
        if (!saveResult) {
            throw new RuntimeException("产品详情创建失败");
        }
    }

    @Override
    public void updateProductDetail(ProductDetailReqDTO requestParam) {
        ProductDetailDO productDetailDO = BeanUtil.copyProperties(requestParam, ProductDetailDO.class);
        LambdaUpdateWrapper<ProductDetailDO> wrapper = Wrappers.lambdaUpdate(ProductDetailDO.class)
                .eq(ProductDetailDO::getProductDetailId, productDetailDO.getProductDetailId())
                .eq(ProductDetailDO::getDelFlag, 0);
        boolean updateResult = baseMapper.update(productDetailDO, wrapper) > 0;
        if (!updateResult) {
            throw new RuntimeException("产品详情更新失败");
        }
    }

    @Override
    public void deleteProductDetail(Long productDetailId) {
        LambdaUpdateWrapper<ProductDetailDO> wrapper = Wrappers.lambdaUpdate(ProductDetailDO.class)
                .eq(ProductDetailDO::getProductDetailId, productDetailId)
                .eq(ProductDetailDO::getDelFlag, 0);
        ProductDetailDO productDetailDO = new ProductDetailDO();
        productDetailDO.setDelFlag(1); // 设置为已删除
        boolean deleteResult = baseMapper.update(productDetailDO, wrapper) > 0;
        if (!deleteResult) {
            throw new RuntimeException("产品详情删除失败");
        }
    }
}
