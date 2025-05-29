package org.liquidice.exmall.pay.dto.resp;

import lombok.Data;

/**
 * 支付响应数据传输对象
 * 用于接收支付相关的响应参数
 */
@Data
public class PayRespDTO {

    /**
     * 支付表自增主键ID-支付流水号
     */
    private Long payId;

    /**
     * 支付类型：收入或支出
     */
    private String payType; // "income"|"outcome"

    /**
     * 付款人UID
     */
    private Long payerUid;

    /**
     * 收款人UID
     */
    private Long payeeUid;

    /**
     * 订单ID（业务层面标识订单的编号）
     */
    private Long orderId;

    /**
     * 支付渠道
     */
    private String payChannel; // "WeChatPay"|"AliPay"|"WalletPay"|"CDKPay"

    /**
     * 支付状态
     */
    private Integer payState = 0; //支付状态：0:待支付 | 1:已支付 | 2:支付失败

    /**
     * 支付金额
     */
    private Double payAmount;
}
