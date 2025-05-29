package org.liquidice.exmall.cart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.cart.dao.entity.CartDO;
import org.liquidice.exmall.cart.dao.entity.ProductDO;
import org.liquidice.exmall.cart.dao.mapper.CartMapper;
import org.liquidice.exmall.cart.dao.mapper.ProductMapper;
import org.liquidice.exmall.cart.dto.req.CartReqDTO;
import org.liquidice.exmall.cart.dto.resp.CartRespDTO;
import org.liquidice.exmall.cart.service.CartService;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, CartDO> implements CartService {

    private final CartMapper baseMapper;
    private final ProductMapper productMapper;

    /**
     * 更新价格
     * @param cartId 购物车ID
     */
    public void updatePrice(Long cartId) {
        LambdaQueryWrapper<CartDO> queryWrapper = Wrappers.lambdaQuery(CartDO.class)
                .eq(CartDO::getCartId, cartId)
                .eq(CartDO::getDelFlag, 0);
        CartDO cartDO = baseMapper.selectOne(queryWrapper);

        // 控制台输出cartDO信息日志
        log.warn(cartDO.toString());

        LambdaQueryWrapper<ProductDO> productQueryWrapper = Wrappers.lambdaQuery(ProductDO.class)
                .eq(ProductDO::getProductId, 1)
                .eq(ProductDO::getDelFlag, 0);
        ProductDO productDO = productMapper.selectOne(productQueryWrapper);

        Double computePrice = cartDO.getProductCount() * (productDO.getProductBaseAmount());

        LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                .eq(CartDO::getCartId, cartId)
                .eq(CartDO::getDelFlag, 0)
                .set(CartDO::getProductPrice, computePrice);
        boolean updated = baseMapper.update(null, updateWrapper) > 0;
        if (!updated) {
            throw new ServiceException("更新价格失败");
        }
    }

    @Override
    public IPage<CartRespDTO> getCartByUserId(Long userId) {
        LambdaQueryWrapper<CartDO> queryWrapper = Wrappers.lambdaQuery(CartDO.class)
                .eq(CartDO::getUid, userId)
                .eq(CartDO::getDelFlag, 0)
                .orderByAsc(CartDO::getCreateTime);
        IPage<CartDO> page = new Page<>(1, 10);
        IPage<CartDO> cartDOIPage = baseMapper.selectPage(page, queryWrapper);
        IPage<CartRespDTO> cartRespDTOIPage = new Page<>(1, 10);
        BeanUtils.copyProperties(cartDOIPage, cartRespDTOIPage);
        return cartRespDTOIPage;
    }

    @Override
    public CartRespDTO getCartById(Long cartId) {
        LambdaQueryWrapper<CartDO> queryWrapper = Wrappers.lambdaQuery(CartDO.class)
                .eq(CartDO::getCartId, cartId)
                .eq(CartDO::getDelFlag, 0);
        CartDO cartDO = baseMapper.selectOne(queryWrapper);
        if (cartDO == null) {
            throw new ServiceException("商品不存在或已被删除");
        }
        CartRespDTO result = new CartRespDTO();
        BeanUtils.copyProperties(cartDO, result);
        return result;
    }

    @Override
    public Boolean addToCart(CartReqDTO requestParam) {
        try {
            CartDO cartDO = new CartDO();
            BeanUtils.copyProperties(requestParam, cartDO);
            LambdaQueryWrapper<CartDO> queryWrapper = Wrappers.lambdaQuery(CartDO.class)
                    .eq(CartDO::getUid, cartDO.getUid())
                    .eq(CartDO::getProductId, cartDO.getProductId())
                    .eq(CartDO::getDelFlag, 0);
            CartDO existingCart = baseMapper.selectOne(queryWrapper);
            if (existingCart != null && existingCart.getProductSkuCode().equals(cartDO.getProductSkuCode())) {
                // 如果购物车中已存在该商品，则更新数量
                cartDO.setCartId(existingCart.getCartId());
                cartDO.setProductCount(existingCart.getProductCount() + requestParam.getProductCount() );
                LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                        .eq(CartDO::getCartId, existingCart.getCartId())
                        .eq(CartDO::getDelFlag, 0);
                boolean update = baseMapper.update(cartDO, updateWrapper) > 0;
                if (!update) {
                    throw new ServiceException("更新购物车失败");
                }
                updatePrice(existingCart.getCartId());
                return true;
            }
            // 如果购物车中不存在该商品，则插入新记录
            boolean save = baseMapper.insert(cartDO) > 0;
            if (!save) {
                throw new ServiceException("添加到购物车失败");
            }
            updatePrice(cartDO.getCartId());
            return true;
        } catch (ServiceException e) {
            return false;
        }

    }

    @Override
    public Boolean updateCart(CartReqDTO requestParam) {
        try {
            LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                    .eq(CartDO::getCartId, requestParam.getCartId())
                    .eq(CartDO::getDelFlag, 0);
            boolean update = baseMapper.update(BeanUtil.toBean(requestParam, CartDO.class), updateWrapper) > 0;
            if (!update) {
                throw new ServiceException("更新购物车失败");
            }
            updatePrice(requestParam.getCartId());
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public Boolean deleteCart(CartReqDTO requestParam) {
        try {
            LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                    .eq(CartDO::getCartId, requestParam.getCartId())
                    .eq(CartDO::getDelFlag, 0)
                    .set(CartDO::getDelFlag, 1);
            boolean remove = baseMapper.update(null, updateWrapper) > 0;
            if (!remove) {
                throw new ServiceException("删除购物车失败");
            }
            updatePrice(requestParam.getCartId());
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
