package org.liquidice.exmall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.product.dao.entity.ProductDO;
import org.liquidice.exmall.product.dao.mapper.ProductMapper;
import org.liquidice.exmall.product.dto.req.ProductReqDTO;
import org.liquidice.exmall.product.dto.resp.ProductRespDTO;
import org.liquidice.exmall.product.service.ProductService;

public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {
    @Override
    public ProductRespDTO getProductById(Long productId) {
        LambdaQueryWrapper<ProductDO> queryWrapper = Wrappers.lambdaQuery(ProductDO.class)
                .eq(ProductDO::getProductId, productId);
        ProductDO productDO = baseMapper.selectOne(queryWrapper);
        if (productDO == null) {
            throw new ServiceException("商品不存在");
        }
        ProductRespDTO result = new ProductRespDTO();
        BeanUtil.copyProperties(productDO, result);
        return result;
    }

    @Override
    public Boolean addProduct(ProductReqDTO requestParam) {
        return null;
    }

    @Override
    public Boolean updateProduct(ProductReqDTO requestParam) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        return null;
    }
}
