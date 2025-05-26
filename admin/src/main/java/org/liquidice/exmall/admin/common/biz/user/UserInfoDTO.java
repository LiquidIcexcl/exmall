package org.liquidice.exmall.admin.common.biz.user;


import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户文中信息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {
    /**
     * 用户ID
     */
    @JSONField(name = "uid")
    private String userId;

    /**
     * 用户名
     */
    private String username;

}
