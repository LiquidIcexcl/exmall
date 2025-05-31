package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liquidice.exmall.product.dao.entity.ProductIconDO;
import org.liquidice.exmall.product.dao.mapper.ProductIconMapper;
import org.liquidice.exmall.product.dto.req.ProductIconReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductIconRespDTO;
import org.liquidice.exmall.product.service.ProductIconService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductIconServiceImpl extends ServiceImpl<ProductIconMapper, ProductIconDO> implements ProductIconService {


    @Override
    public List<ProductIconRespDTO> getProductIconById(Long productId) {
        LambdaQueryWrapper<ProductIconDO> queryWrapper = Wrappers.lambdaQuery(ProductIconDO.class)
                .eq(ProductIconDO::getProductId, productId)
                .eq(ProductIconDO::getDelFlag, 0);
        List<ProductIconDO> productIconList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(productIconList, ProductIconRespDTO.class);
    }

    @Override
    public void createProductIcon(ProductIconReqDTO requestParam) {
        ProductIconDO productIconDO = BeanUtil.copyProperties(requestParam, ProductIconDO.class);
        boolean saveResult = baseMapper.insert(productIconDO) > 0;
        if (!saveResult) {
            throw new RuntimeException("产品图标创建失败");
        }
    }

    @Override
    public void updateProductIcon(ProductIconReqDTO requestParam) {
        ProductIconDO productIconDO = BeanUtil.copyProperties(requestParam, ProductIconDO.class);
        LambdaUpdateWrapper<ProductIconDO> updateWrapper = Wrappers.lambdaUpdate(ProductIconDO.class)
                .eq(ProductIconDO::getProductIconId, productIconDO.getProductIconId())
                .eq(ProductIconDO::getDelFlag, 0);
        boolean updateResult = baseMapper.update(productIconDO, updateWrapper) > 0;
        if (!updateResult) {
            throw new RuntimeException("产品图标更新失败");
        }
    }

    @Override
    public void deleteProductIcon(Long productIconId) {
        LambdaUpdateWrapper<ProductIconDO> updateWrapper = Wrappers.lambdaUpdate(ProductIconDO.class)
                .eq(ProductIconDO::getProductIconId, productIconId)
                .eq(ProductIconDO::getDelFlag, 0);
        ProductIconDO productIconDO = new ProductIconDO();
        productIconDO.setDelFlag(1); // 设置为已删除
        boolean deleteResult = baseMapper.update(productIconDO, updateWrapper) > 0;
        if (!deleteResult) {
            throw new RuntimeException("产品图标删除失败");
        }
    }
}
