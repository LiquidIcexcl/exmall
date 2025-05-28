package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.product.dao.entity.ProductDO;
import org.liquidice.exmall.product.dao.mapper.ProductMapper;
import org.liquidice.exmall.product.dto.req.ProductReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductRespDTO;
import org.liquidice.exmall.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {

    /**
     * 验证商品是否存在
     */
    private Boolean isProductExists(Long productId) {
        LambdaQueryWrapper<ProductDO> queryWrapper = Wrappers.lambdaQuery(ProductDO.class)
                .eq(ProductDO::getProductId, productId);
        ProductDO productDO = baseMapper.selectOne(queryWrapper);
        return productDO != null && productDO.getDelFlag() != 1;
    }

    @Override
    public ProductRespDTO getProductById(Long productId) {
        if (!isProductExists(productId)) {
            throw new ServiceException("商品不存在或已被删除");
        }
        LambdaQueryWrapper<ProductDO> queryWrapper = Wrappers.lambdaQuery(ProductDO.class)
                .eq(ProductDO::getProductId, productId);
        ProductDO productDO = baseMapper.selectOne(queryWrapper);
        ProductRespDTO result = new ProductRespDTO();
        BeanUtil.copyProperties(productDO, result);
        return result;
    }

    @Override
    public Boolean addProduct(ProductReqDTO requestParam) {
        try {
            ProductDO productDO = new ProductDO();
            BeanUtil.copyProperties(requestParam, productDO);
            boolean save = baseMapper.insert(productDO) > 0;
            if (!save) {
                throw new ServiceException("添加商品失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public Boolean updateProduct(ProductReqDTO requestParam) {
        try {
            if (!isProductExists(requestParam.getProductId())) {
                throw new ServiceException("商品不存在或已被删除");
            }
            LambdaUpdateWrapper<ProductDO> updateWrapper = Wrappers.lambdaUpdate(ProductDO.class)
                    .eq(ProductDO::getProductId, requestParam.getProductId())
                    .eq(ProductDO::getDelFlag, 0); // 确保未被删除
            boolean update = baseMapper.update(BeanUtil.toBean(requestParam, ProductDO.class), updateWrapper) > 0;
            if (!update) {
                throw new ServiceException("更新商品失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        try {
            if (!isProductExists(productId)) {
                throw new ServiceException("商品不存在或已被删除");
            }
            LambdaUpdateWrapper <ProductDO> updateWrapper = Wrappers.lambdaUpdate(ProductDO.class)
                    .eq(ProductDO::getProductId, productId)
                    .set(ProductDO::getDelFlag, 1); // 设置删除标志
            ProductRespDTO product = getProductById(productId);
            boolean update = baseMapper.update(BeanUtil.toBean(product, ProductDO.class), updateWrapper) > 0;
            if (!update) {
                throw new ServiceException("删除商品失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
