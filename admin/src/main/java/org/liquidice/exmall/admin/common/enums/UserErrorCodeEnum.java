package org.liquidice.exmall.admin.common.enums;


import org.liquidice.exmall.framework.errorcode.IErrorCode;


/**
 * 用户相关错误码枚举
 */
public enum UserErrorCodeEnum implements IErrorCode {

    USER_NULL("B000200", "用户记录不存在"),

    USER_NAME_EXIST("B000201", "用户名已存在"),

    USER_EXIST("B000202", "用户记录已存在"),

    USER_SAVE_ERROR("B000203", "用户记录保存失败"),

    USER_IS_MERCHANT("B000204", "用户不是商户身份"),

    USER_SHOP_IS_NULL("B000205", "用户店铺记录不存在"),
    USER_SHOP_IS_EXIST( "B000206", "用户店铺记录已存在"),;

    private final String code;
    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
