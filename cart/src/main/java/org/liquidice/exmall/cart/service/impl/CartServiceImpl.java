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
import org.liquidice.exmall.cart.dao.mapper.CartMapper;
import org.liquidice.exmall.cart.dto.resp.CartRespDTO;
import org.liquidice.exmall.cart.service.CartService;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, CartDO> implements CartService {

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
    public Boolean addToCart(CartRespDTO requestParam) {
        try {
            CartDO cartDO = new CartDO();
            BeanUtils.copyProperties(requestParam, cartDO);
            boolean save = baseMapper.insert(cartDO) > 0;
            if (!save) {
                throw new ServiceException("添加到购物车失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }

    }

    @Override
    public Boolean updateCart(CartRespDTO requestParam) {
        try {
            LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                    .eq(CartDO::getCartId, requestParam.getCartId())
                    .eq(CartDO::getDelFlag, 0);
            boolean update = baseMapper.update(BeanUtil.toBean(requestParam, CartDO.class), updateWrapper) > 0;
            if (!update) {
                throw new ServiceException("更新购物车失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public Boolean deleteCart(CartRespDTO requestParam) {
        try {
            LambdaUpdateWrapper<CartDO> updateWrapper = Wrappers.lambdaUpdate(CartDO.class)
                    .eq(CartDO::getCartId, requestParam.getCartId())
                    .eq(CartDO::getDelFlag, 0)
                    .set(CartDO::getDelFlag, 1);
            boolean remove = baseMapper.update(null, updateWrapper) > 0;
            if (!remove) {
                throw new ServiceException("删除购物车失败");
            }
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
