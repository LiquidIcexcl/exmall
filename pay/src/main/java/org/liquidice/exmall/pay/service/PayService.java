package org.liquidice.exmall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.pay.dao.entity.PayDO;
import org.liquidice.exmall.pay.dto.req.PayReqDTO;
import org.liquidice.exmall.pay.dto.resp.PayRespDTO;

import java.util.List;

public interface PayService extends IService<PayDO> {
    /**
     * 根据支付ID查询支付信息
     *
     * @param payId 支付ID
     * @return payRespDTO 支付响应DTO
     */
    PayRespDTO getPayByPayId(Long payId);

    /**
     * 根据用户ID查询支付信息
     *
     * @param uid 用户ID
     * @return Result<List<payRespDTO>> 支付响应DTO列表
     */
    List<PayRespDTO> getPayByUid(Long uid);

    /**
     * 根据订单ID查询支付信息
     *
     * @param orderId 订单ID
     * @return Result<List<payRespDTO>> 支付响应DTO列表
     */
    PayRespDTO getPayByOrderId(Long orderId, Long uid);

    /**
     * 创建支付记录--订单根据
     *
     * @param requestParam payReqDTO 请求参数
     * @return Boolean 是否创建成功
     */
    Boolean createPay(PayReqDTO requestParam);

    /**
     * 创建支付记录--钱包充值
     *
     * @param requestParam payReqDTO 请求参数
     */
    Boolean createWalletRecharge(PayReqDTO requestParam);


}
