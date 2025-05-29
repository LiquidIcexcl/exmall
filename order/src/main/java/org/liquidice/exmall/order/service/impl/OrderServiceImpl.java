package org.liquidice.exmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liquidice.exmall.order.config.SnowFlakeIdGenerator;
import org.liquidice.exmall.cart.dto.req.CartReqDTO;
import org.liquidice.exmall.cart.service.CartService;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.order.dao.entity.OrderDO;
import org.liquidice.exmall.order.dao.mapper.OrderMapper;
import org.liquidice.exmall.order.dto.req.OrderReqDTO;
import org.liquidice.exmall.order.dto.resp.OrderRespDTO;
import org.liquidice.exmall.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口层实现 服务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService{

    private final SnowFlakeIdGenerator idGenerator;


    @Override
    public List<OrderRespDTO> getOrderByUid(Long uid) {
        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.lambdaQuery(OrderDO.class)
                .eq(OrderDO::getUid, uid)
                .eq(OrderDO::getDelFlag, 0);
        List<OrderDO> orderDOList = baseMapper.selectList(queryWrapper);
        if (orderDOList == null || orderDOList.isEmpty()) {
            throw new ServiceException("找不到用户ID为 " + uid + " 的订单");
        }
        List<OrderRespDTO> listOrderRespDTO = new ArrayList<>(orderDOList.size());
        for (OrderDO orderDO : orderDOList) {
            OrderRespDTO orderRespDTO = new OrderRespDTO();
            BeanUtils.copyProperties(orderDO, orderRespDTO);
            listOrderRespDTO.add(orderRespDTO);
        }
        return listOrderRespDTO;
    }

    @Override
    public List<OrderRespDTO> getOrderByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.lambdaQuery(OrderDO.class)
                .eq(OrderDO::getOrderId, orderId)
                .eq(OrderDO::getDelFlag, 0);
        List<OrderDO> orderDOList = baseMapper.selectList(queryWrapper);
        if (orderDOList == null) {
            throw new ServiceException("找不到订单ID为 " + orderId + " 的订单");
        }
        List<OrderRespDTO> orderRespDTOList = new ArrayList<>(orderDOList.size());
        for (OrderDO orderDO : orderDOList) {
            OrderRespDTO orderRespDTO = new OrderRespDTO();
            BeanUtils.copyProperties(orderDO, orderRespDTO);
            orderRespDTOList.add(orderRespDTO);
        }
        return orderRespDTOList;
    }

    @Override
    public Boolean createOrder(List<CartReqDTO> requestParam) {
        Long orderId = idGenerator.nextId();
        for(CartReqDTO cartReqDTO : requestParam) {
            OrderDO orderDO = OrderDO.builder()
                    .uid(cartReqDTO.getUid())
                    .orderId(orderId)
                    .productId(cartReqDTO.getProductId())
                    .productSkuCode(cartReqDTO.getProductSkuCode())
                    .productCount(cartReqDTO.getProductCount())
                    .totalPrice(cartReqDTO.getProductPrice())
                    .build();
            boolean saved = baseMapper.insert(orderDO) > 0;
            if (!saved) {
                throw new ServiceException("创建订单失败");
            }
        }
        return true;
    }

    @Override
    public void updateOrder(OrderReqDTO requestParam) {
        LambdaUpdateWrapper<OrderDO> updateWrapper = Wrappers.lambdaUpdate(OrderDO.class)
                .eq(OrderDO::getOrderId, requestParam.getOrderId())
                .eq(OrderDO::getDelFlag, 0);

        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(requestParam, orderDO);
        boolean updated = baseMapper.update(orderDO, updateWrapper) > 0;
        if (!updated) {
            throw new ServiceException("更新订单失败");
        }

    }

    @Override
    public void deleteOrder(OrderReqDTO requestParam) {
        LambdaUpdateWrapper<OrderDO> updateWrapper = Wrappers.lambdaUpdate(OrderDO.class)
                .eq(OrderDO::getOrderId, requestParam.getOrderId())
                .eq(OrderDO::getDelFlag, 0);

        OrderDO orderDO = new OrderDO();
        orderDO.setDelFlag(1); // 设置删除标志
        boolean deleted = baseMapper.update(orderDO, updateWrapper) > 0;
        if (!deleted) {
            throw new ServiceException("删除订单失败");
        }

    }

    @Override
    public Double calculateTotalPrice(Long orderId) {
        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.lambdaQuery(OrderDO.class)
                .eq(OrderDO::getOrderId, orderId)
                .eq(OrderDO::getDelFlag, 0);
        List<OrderDO> orderDOList = baseMapper.selectList(queryWrapper);
        if (orderDOList == null || orderDOList.isEmpty()) {
            throw new ServiceException("找不到订单ID为 " + orderId + " 的订单");
        }

        Double totalPrice = 0D;

        for (OrderDO orderDO : orderDOList) {
            totalPrice += orderDO.getTotalPrice();
        }

        return totalPrice;
    }
}
