package org.liquidice.exmall.cart.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.cart.dto.req.CartReqDTO;
import org.liquidice.exmall.cart.dto.resp.CartRespDTO;
import org.liquidice.exmall.cart.service.CartService;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车控制器
 */
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 根据用户ID查询购物车信息
     */
    @GetMapping("/api/exmall/cart/v1/cart/{userId}")
    public Result<IPage<CartRespDTO>> getCartByUserId(@PathVariable Long userId) {
        IPage<CartRespDTO> cartPage = cartService.getCartByUserId(userId);
        return Results.success(cartPage);
    }

    /**
     * 购物车ID查询购物车内商品信息
     */
    @GetMapping("/api/exmall/cart/v1/cart/id/{cartId}")
    public Result<CartRespDTO> getCartById(@PathVariable Long cartId) {
        CartRespDTO cart = cartService.getCartById(cartId);
        return Results.success(cart);
    }

    /**
     * 添加商品到购物车
     */
    @PostMapping("/api/exmall/cart/v1/cart")
    public Result<Boolean> addToCart(@RequestBody CartReqDTO cartReqDTO) {
        Boolean result = cartService.addToCart(cartReqDTO);
        return Results.success(result);
    }

    /**
     * 更新购物车中的商品数量
     */
    @PutMapping("/api/exmall/cart/v1/cart")
    public Result<Boolean> updateCart(@RequestBody CartReqDTO cartReqDTO) {
        Boolean result = cartService.updateCart(cartReqDTO);
        return Results.success(result);
    }

    /**
     * 删除购物车中的商品
     */
    @DeleteMapping("/api/exmall/cart/v1/cart")
    public Result<Boolean> deleteCart(@RequestBody CartReqDTO cartReqDTO) {
        Boolean result = cartService.deleteCart(cartReqDTO);
        return Results.success(result);
    }





}
