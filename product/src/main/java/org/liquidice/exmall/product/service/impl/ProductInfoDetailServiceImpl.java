package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liquidice.exmall.product.dao.entity.ProductInfoDO;
import org.liquidice.exmall.product.dao.entity.ProductInfoDetailDO;
import org.liquidice.exmall.product.dao.mapper.ProductInfoDetailMapper;
import org.liquidice.exmall.product.dao.mapper.ProductInfoMapper;
import org.liquidice.exmall.product.dto.req.ProductInfoDetailReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductInfoDetailRespDTO;
import org.liquidice.exmall.product.service.ProductInfoDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoDetailServiceImpl extends ServiceImpl<ProductInfoDetailMapper, ProductInfoDetailDO> implements ProductInfoDetailService {
    @Override
    public List<ProductInfoDetailRespDTO> getProductInfoDetailByProductInfoId(Long productInfoId) {
        LambdaQueryWrapper<ProductInfoDetailDO> wrapper = Wrappers.lambdaQuery(ProductInfoDetailDO.class)
                .eq(ProductInfoDetailDO::getProductInfoId, productInfoId)
                .eq(ProductInfoDetailDO::getDelFlag, 0);
        List<ProductInfoDetailDO> productInfoDetailList = baseMapper.selectList(wrapper);
        return BeanUtil.copyToList(productInfoDetailList, ProductInfoDetailRespDTO.class);
    }

    @Override
    public void createProductInfoDetail(ProductInfoDetailReqDTO requestParam) {
        ProductInfoDetailDO productInfoDetailDO = BeanUtil.copyProperties(requestParam, ProductInfoDetailDO.class);
        boolean save = baseMapper.insert(productInfoDetailDO) > 0;
        if (!save) {
            throw new RuntimeException("信息细节创建失败");
        }
    }

    @Override
    public void updateProductInfoDetail(ProductInfoDetailReqDTO requestParam) {
        ProductInfoDetailDO productInfoDetailDO = BeanUtil.copyProperties(requestParam, ProductInfoDetailDO.class);
        LambdaUpdateWrapper<ProductInfoDetailDO> updateWrapper = Wrappers.lambdaUpdate(ProductInfoDetailDO.class)
                .eq(ProductInfoDetailDO::getProductInfoDetailId, productInfoDetailDO.getProductInfoDetailId())
                .eq(ProductInfoDetailDO::getDelFlag, 0);
        boolean updateResult = baseMapper.update(productInfoDetailDO, updateWrapper) > 0;
        if (!updateResult) {
            throw new RuntimeException("信息细节更新失败");
        }
    }

    @Override
    public void deleteProductInfoDetail(Long productInfoDetailId) {
        LambdaUpdateWrapper<ProductInfoDetailDO> updateWrapper = Wrappers.lambdaUpdate(ProductInfoDetailDO.class)
                .eq(ProductInfoDetailDO::getProductInfoDetailId, productInfoDetailId)
                .eq(ProductInfoDetailDO::getDelFlag, 0);
        ProductInfoDetailDO productInfoDetailDO = new ProductInfoDetailDO();
        productInfoDetailDO.setDelFlag(1); // 设置为已删除
        boolean deleteResult = baseMapper.update(productInfoDetailDO, updateWrapper) > 0;
        if (!deleteResult) {
            throw new RuntimeException("信息细节删除失败");
        }
    }
}
