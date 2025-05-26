package org.liquidice.exmall.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 用户实体类
 */
@Data
@TableName("t_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {
    /**
     * uid
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 是否为商户
     */
    private Boolean isMerchant;

    /**
     * 商家编号
     */
    private String shopId;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
