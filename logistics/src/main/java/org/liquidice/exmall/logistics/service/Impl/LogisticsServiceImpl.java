package org.liquidice.exmall.logistics.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.logistics.dao.entity.LogisticsDO;
import org.liquidice.exmall.logistics.dao.mapper.LogisticsMapper;
import org.liquidice.exmall.logistics.dto.req.LogisticsReqDTO;
import org.liquidice.exmall.logistics.dto.resp.LogisticsRespDTO;
import org.liquidice.exmall.logistics.service.LogisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, LogisticsDO> implements LogisticsService {

    @Override
    public List<LogisticsRespDTO> getLogisticsByReceiverId(Long userId) {
        LambdaQueryWrapper<LogisticsDO> queryWrapper = Wrappers.lambdaQuery(LogisticsDO.class)
                .eq(LogisticsDO::getReceiverId, userId)
                .eq(LogisticsDO::getDelFlag, 0);
        List<LogisticsDO> logisticsDO = baseMapper.selectList(queryWrapper);
        if (logisticsDO == null) {
            throw new ServiceException("物流信息不存在");
        }
        return BeanUtil.copyToList(logisticsDO, LogisticsRespDTO.class);
    }

    @Override
    public List<LogisticsRespDTO> getLogisticsByShopId(Long shopId) {
        LambdaQueryWrapper<LogisticsDO> queryWrapper = Wrappers.lambdaQuery(LogisticsDO.class)
                .eq(LogisticsDO::getSenderId, shopId)
                .eq(LogisticsDO::getDelFlag, 0);
        List<LogisticsDO> logisticsDO = baseMapper.selectList(queryWrapper);
        if (logisticsDO == null) {
            throw new ServiceException("物流信息不存在");
        }
        return BeanUtil.copyToList(logisticsDO, LogisticsRespDTO.class);
    }

    @Override
    public LogisticsRespDTO getLogisticsById(Long logisticsId) {
        LambdaQueryWrapper<LogisticsDO> queryWrapper = Wrappers.lambdaQuery(LogisticsDO.class)
                .eq(LogisticsDO::getId, logisticsId)
                .eq(LogisticsDO::getDelFlag, 0);
        LogisticsDO logisticsDO = baseMapper.selectOne(queryWrapper);
        if (logisticsDO == null) {
            throw new ServiceException("物流信息不存在");
        }
        return BeanUtil.copyProperties(logisticsDO, LogisticsRespDTO.class);
    }

    @Override
    public LogisticsRespDTO createLogistics(LogisticsReqDTO requestParam) {
        LogisticsDO logisticsDO = BeanUtil.copyProperties(requestParam, LogisticsDO.class);
        logisticsDO.setDelFlag(0); // 设置未删除标志
        boolean saveResult = baseMapper.insert(logisticsDO) > 0;
        if (!saveResult) {
            throw new ServiceException("创建物流信息失败");
        }
        return BeanUtil.copyProperties(logisticsDO, LogisticsRespDTO.class);
    }

    @Override
    public void updateLogistics(LogisticsReqDTO requestParam) {
        LambdaUpdateWrapper<LogisticsDO> updateWrapper = Wrappers.lambdaUpdate(LogisticsDO.class)
                .eq(LogisticsDO::getId, requestParam.getId());
        LogisticsDO logisticsDO = BeanUtil.copyProperties(requestParam, LogisticsDO.class);
        boolean updateResult = baseMapper.update(logisticsDO, updateWrapper) > 0;
        if (!updateResult) {
            throw new ServiceException("更新物流信息失败");
        }
    }

    @Override
    public void deleteLogistics(LogisticsReqDTO requestParam) {
        LambdaUpdateWrapper<LogisticsDO> updateWrapper = Wrappers.lambdaUpdate(LogisticsDO.class)
                .eq(LogisticsDO::getId, requestParam.getId());
        LogisticsDO logisticsDO = new LogisticsDO();
        logisticsDO.setDelFlag(1); // 设置为已删除
        boolean deleteResult = baseMapper.update(logisticsDO, updateWrapper) > 0;
        if (!deleteResult) {
            throw new ServiceException("删除物流信息失败");
        }
    }
}
