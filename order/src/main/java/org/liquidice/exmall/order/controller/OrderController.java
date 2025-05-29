package org.liquidice.exmall.order.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.order.dto.req.OrderReqDTO;
import org.liquidice.exmall.order.dto.resp.OrderRespDTO;
import org.liquidice.exmall.order.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.liquidice.exmall.cart.dto.req.CartReqDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 查询订单by uid
     */
    @GetMapping("/api/exmall/order/v1/order/{uid}")
    public Result<List<OrderRespDTO>> getOrderByUid(@PathVariable("uid") Long uid) {
        return Results.success(orderService.getOrderByUid(uid));
    }

    /**
     * 查询订单by orderId
     */
    @GetMapping("/api/exmall/order/v1/order/orderId/{orderId}")
    public Result<OrderRespDTO> getOrderByOrderId(@PathVariable("orderId") Long orderId) {
        return Results.success(orderService.getOrderByOrderId(orderId));
    }

    /**
     * 新增订单（传入List<CartReqDTO>）
     */
    @PostMapping("/api/exmall/order/v1/order")
    public Result<Boolean> createOrder(@RequestBody List<CartReqDTO> requestParam) {
        Boolean result = orderService.createOrder(requestParam);
        return Results.success(result);
    }

    /**
         * 更新订单状态
     */
    @PutMapping("/api/exmall/order/v1/order")
    public Result<Void> updateOrder(@RequestBody OrderReqDTO requestParam) {
        orderService.updateOrder(requestParam);
        return Results.success();
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/api/exmall/order/v1/order")
    public Result<Void> deleteOrder(@RequestBody OrderReqDTO requestParam) {
        orderService.deleteOrder(requestParam);
        return Results.success();
    }

    /**
     * 计算订单总价
     */
    @PostMapping("/api/exmall/order/v1/order/total-price")
    public Result<Long> calculateTotalPrice(@RequestBody List<CartReqDTO> requestParam) {
        Long totalPrice = orderService.calculateTotalPrice(requestParam);
        return Results.success(totalPrice);
    }
}
