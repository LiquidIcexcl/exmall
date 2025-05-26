package org.liquidice.exmall.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

/**
 * 用户配置实体类
 */
@Data
@TableName("t_user_configuration")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConfigurationDO extends BaseDO {
    /**
     * 用户ID
     */
    private String uid;

    /**
     * 用户是否使用深色模式
     */
    private String isNight;
}
