package org.liquidice.exmall.admin.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.admin.dto.req.UserShopReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserShopRespDTO;
import org.liquidice.exmall.admin.service.UserShopService;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserShopController {
    private final UserShopService userShopService;

    /**
     * 根据用户id获取商户信息
     * @param uid 用户ID
     */
    @GetMapping("/api/exmall/admin/v1/shop/{uid}")
    public Result<UserShopRespDTO> getShopByUsername(
            @PathVariable("uid") Long uid) {
        return Results.success(userShopService.getShopByUid(uid));
    }

    /**
     * 用户创建商户
     * @param requestParam 商户信息
     * @return 商户信息
     */
    @PostMapping("/api/exmall/admin/v1/shop")
    public Result<UserShopRespDTO> createShop(@RequestBody UserShopReqDTO requestParam) {
        return Results.success(userShopService.createShop(requestParam));
    }

    /**
     * 更新商户信息
     * @param requestParam 商户信息
     */
    @PutMapping("/api/exmall/admin/v1/shop/")
    public Result<Void> updateShop(@RequestBody UserShopReqDTO requestParam) {
        userShopService.updateShop(requestParam);
        return Results.success();
    }
}
