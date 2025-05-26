package org.liquidice.exmall.admin.common.constant;

/**
 * 短链接后管 Redis缓存常量
 */
public class RedisCacheConstant {
    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "exmall:lock_user-register";


    /**
     * 用户登入缓存标识
     */
    public static final String USER_LOGIN_KEY = "exmall:login:";
}
