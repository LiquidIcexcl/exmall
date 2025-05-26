package org.liquidice.exmall.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 用户钱包实体类
 */
@Data
@TableName("t_user_wallet")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletDO extends BaseDO {
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
