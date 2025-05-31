package org.liquidice.exmall.logistics.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.logistics.dto.req.LogisticsReqDTO;
import org.liquidice.exmall.logistics.dto.resp.LogisticsRespDTO;
import org.liquidice.exmall.logistics.service.LogisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogisticsController {

    private final LogisticsService logisticsService;

    /**
     * 用户Id查询物流信息
     */
    @GetMapping("/api/exmall/logistics/v1/logistics/{userId}")
    public Result<List<LogisticsRespDTO>> getLogistics(@PathVariable Long userId) {
        return Results.success(logisticsService.getLogisticsByReceiverId(userId));
    }

    /**
     * 商户Id查询物流信息
     */
    @GetMapping("/api/exmall/logistics/v1/logistics/shop/{shopId}")
    public Result<List<LogisticsRespDTO>> getLogisticsByShopId(@PathVariable Long shopId) {
        return Results.success(logisticsService.getLogisticsByShopId(shopId));
    }

    /**
     * 物流Id查询物流信息
     */
    @GetMapping("/api/exmall/logistics/v1/logistics/id/{logisticsId}")
    public Result<LogisticsRespDTO> getLogisticsById(@PathVariable Long logisticsId) {
        return Results.success(logisticsService.getLogisticsById(logisticsId));
    }

    /**
     * 创建物流信息
     */
    @PostMapping("/api/exmall/logistics/v1/logistics")
    public Result<LogisticsRespDTO> createLogistics(@RequestBody LogisticsReqDTO requestParam) {
        return Results.success(logisticsService.createLogistics(requestParam));
    }

    /**
     * 更新物流信息
     */
    @PutMapping("/api/exmall/logistics/v1/logistics")
    public Result<Void> updateLogistics(@RequestBody LogisticsReqDTO requestParam) {
        logisticsService.updateLogistics(requestParam);
        return Results.success();
    }

    /**
     * 删除物流信息
     */
    @DeleteMapping("/api/exmall/logistics/v1/logistics")
    public Result<Void> deleteLogistics(@RequestBody LogisticsReqDTO requestParam) {
        logisticsService.deleteLogistics(requestParam);
        return Results.success();
    }

}
