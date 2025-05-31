package org.liquidice.exmall.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.logistics.dao.entity.LogisticsDO;
import org.liquidice.exmall.logistics.dto.req.LogisticsReqDTO;
import org.liquidice.exmall.logistics.dto.resp.LogisticsRespDTO;

import java.util.List;

public interface LogisticsService extends IService<LogisticsDO> {


    List<LogisticsRespDTO> getLogisticsByReceiverId(Long userId);

    List<LogisticsRespDTO> getLogisticsByShopId(Long shopId);

    LogisticsRespDTO getLogisticsById(Long logisticsId);

    LogisticsRespDTO createLogistics(LogisticsReqDTO requestParam);

    void updateLogistics(LogisticsReqDTO requestParam);

    void deleteLogistics(LogisticsReqDTO requestParam);
}
