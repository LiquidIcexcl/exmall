package org.liquidice.exmall.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

@Data
@TableName("t_user_shop")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShopDO extends BaseDO {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 商户ID
     */
    private Long shopId;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 商户头像
     */
    private String shopAvatarUrl;

    /**
     * 商户简介
     */
    private String shopDescription;

    /**
     * 商户地址-省份
     */
    private String shopProvince;

    /**
     * 商户地址-城市
     */
    private String shopCity;

    /**
     * 商户具体地址
     */
    private String shopAddress;

    /**
     * 商户电话
     */
    private String shopPhone;

    /**
     * 商户邮箱
     */
    private String shopEmail;
}
