package org.liquidice.exmall.admin.dto.req;

import lombok.Data;

/**
 * 用户修改请求DTO
 */
@Data
public class UserUpdateReqDTO {
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
}
