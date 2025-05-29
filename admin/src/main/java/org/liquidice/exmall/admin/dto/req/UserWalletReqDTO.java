package org.liquidice.exmall.admin.dto.req;

import lombok.Data;

@Data
public class UserWalletReqDTO {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 钱包余额
     */
    private Double balance;

    /**
     * 钱包总收入
     */
    private Double totalIncome;

    /**
     * 钱包总支出
     */
    private Double totalExpenditure;
}
