package org.liquidice.exmall.pay.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.pay.dto.req.PayReqDTO;
import org.liquidice.exmall.pay.dto.resp.PayRespDTO;
import org.liquidice.exmall.pay.service.PayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    /**
     * 支付流水号查询流水信息
     */
    @GetMapping("/api/exmall/pay/v1/pay/{payId}")
    public Result<PayRespDTO> getPayByPayId(@PathVariable Long payId) {
        return Results.success(payService.getPayByPayId(payId));
    }

    /**
     * 用户ID查询流水信息
     */
    @GetMapping("/api/exmall/pay/v1/pay/uid/{uid}")
    public Result<List<PayRespDTO>> getPayByUid(@PathVariable Long uid) {
        return Results.success(payService.getPayByUid(uid));
    }

    /**
     * 订单ID查询流水信息
     */
    @GetMapping("/api/exmall/pay/v1/pay/orderId/")
    public Result<PayRespDTO> getPayByOrderId(
            @RequestParam("orderId") Long orderId,
            @RequestParam("uid") Long uid) {
        return Results.success(payService.getPayByOrderId(orderId,uid));
    }

    /**
     * 订单支付
     */
    @PostMapping("/api/exmall/pay/v1/pay")
    public Result<Boolean> createPay(@RequestBody PayReqDTO requestParam) {
        Boolean result = payService.createPay(requestParam);
        return Results.success(result);
    }

    /**
     * 钱包充值
     */
    @PostMapping("/api/exmall/pay/v1/pay/wallet")
    public Result<Boolean> createWalletPay(@RequestBody PayReqDTO requestParam) {
        Boolean result = payService.createWalletRecharge(requestParam);
        return Results.success(result);
    }


}
