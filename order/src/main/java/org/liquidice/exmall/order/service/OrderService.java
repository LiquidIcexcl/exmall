package org.liquidice.exmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.cart.dto.req.CartReqDTO;
import org.liquidice.exmall.order.dao.entity.OrderDO;
import org.liquidice.exmall.order.dto.req.OrderReqDTO;
import org.liquidice.exmall.order.dto.resp.OrderRespDTO;

import java.util.List;

/**
 * 订单接口层
 */
public interface OrderService extends IService<OrderDO> {


    List<OrderRespDTO> getOrderByUid(Long uid);

    List<OrderRespDTO> getOrderByOrderId(Long orderId);

    Boolean createOrder(List<CartReqDTO> requestParam);

    void updateOrder(OrderReqDTO requestParam);

    void deleteOrder(OrderReqDTO requestParam);

    Double calculateTotalPrice(Long orderId);
}
