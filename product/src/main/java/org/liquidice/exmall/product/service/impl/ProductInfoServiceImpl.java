package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.product.dao.entity.ProductInfoDO;
import org.liquidice.exmall.product.dao.mapper.ProductInfoMapper;
import org.liquidice.exmall.product.dto.req.ProductInfoReqDTO;
import org.liquidice.exmall.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfoDO> implements ProductInfoService {
    @Override
    public List<ProductInfoReqDTO> getProductInfoById(Long productId) {
        LambdaQueryWrapper<ProductInfoDO> queryWrapper = Wrappers.lambdaQuery(ProductInfoDO.class)
                .eq(ProductInfoDO::getProductId, productId)
                .eq(ProductInfoDO::getDelFlag, 0);
        List<ProductInfoDO> productInfoList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(productInfoList, ProductInfoReqDTO.class);

    }

    @Override
    public void createProductInfo(ProductInfoReqDTO requestParam) {
        ProductInfoDO productInfoDO = BeanUtil.copyProperties(requestParam, ProductInfoDO.class);
        boolean saveResult = baseMapper.insert(productInfoDO) > 0;
        if (!saveResult) {
            throw new ServiceException("信息创建失败");
        }
    }

    @Override
    public void updateProductInfo(ProductInfoReqDTO requestParam) {
        ProductInfoDO productInfoDO = BeanUtil.copyProperties(requestParam, ProductInfoDO.class);
        LambdaUpdateWrapper<ProductInfoDO> updateWrapper = Wrappers.lambdaUpdate(ProductInfoDO.class)
                .eq(ProductInfoDO::getProductInfoId, productInfoDO.getProductId())
                .eq(ProductInfoDO::getDelFlag, 0);
        boolean updateResult = baseMapper.update(productInfoDO, updateWrapper) > 0;
        if (!updateResult) {
            throw new ServiceException("信息更新失败");
        }
    }

    @Override
    public void deleteProductInfo(Long productInfoId) {
        LambdaUpdateWrapper<ProductInfoDO> updateWrapper = Wrappers.lambdaUpdate(ProductInfoDO.class)
                .eq(ProductInfoDO::getProductInfoId, productInfoId)
                .eq(ProductInfoDO::getDelFlag, 0);
        ProductInfoDO productInfoDO = new ProductInfoDO();
        productInfoDO.setDelFlag(1); // 设置为已删除
        boolean deleteResult = baseMapper.update(productInfoDO, updateWrapper) > 0;
        if (!deleteResult) {
            throw new ServiceException("信息删除失败");
        }

    }
}
