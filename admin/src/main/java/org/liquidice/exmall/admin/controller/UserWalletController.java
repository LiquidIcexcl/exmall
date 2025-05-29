package org.liquidice.exmall.admin.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.admin.dto.resp.UserWalletRespDTO;
import org.liquidice.exmall.admin.service.UserWalletService;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserWalletController {
    private final UserWalletService userWalletService;
    /**
     * uid获取用户钱包
     */
    @GetMapping("/api/exmall/admin/v1/wallet/{uid}")
    public Result<UserWalletRespDTO> getUserWalletByUid(@PathVariable("uid") Long uid) {
        return Results.success(userWalletService.getUserWalletByUid(uid));
    }
}
