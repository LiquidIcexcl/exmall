package org.liquidice.exmall.cart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.cart.dao.entity.CartDO;
import org.liquidice.exmall.cart.dto.resp.CartRespDTO;

public interface CartService extends IService<CartDO> {

    /**
     * 根据用户ID查询购物车
     *
     * @param userId 用户ID
     * @return 购物车信息
     */
    IPage<CartRespDTO> getCartByUserId(Long userId);

    /**
     * 根据购物车ID查询购物车
     *
     * @param cartId 购物车ID
     * @return 购物车信息
     */
    CartRespDTO getCartById(Long cartId);

    /**
     * 添加商品到购物车
     *
     * @param requestParam 购物车响应DTO
     * @return 是否添加成功
     */
    Boolean addToCart(CartRespDTO requestParam);

    /**
     * 更新购物车中的商品信息
     *
     * @param requestParam 购物车响应DTO
     * @return 是否更新成功
     */
    Boolean updateCart(CartRespDTO requestParam);

    /**
     * 删除购物车中的商品
     *
     * @param requestParam 购物车响应DTO
     * @return 是否删除成功
     */
    Boolean deleteCart(CartRespDTO requestParam);
}
