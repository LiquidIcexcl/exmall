package org.liquidice.exmall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.admin.dao.entity.UserDO;
import org.liquidice.exmall.admin.dto.req.UserLoginReqDTO;
import org.liquidice.exmall.admin.dto.req.UserRegisterReqDTO;
import org.liquidice.exmall.admin.dto.req.UserUpdateReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserLoginRespDTO;
import org.liquidice.exmall.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 用户存在返回true，否则返回false
     */
    Boolean hasUsername(String username);

    /**
     * 用户注册
     * @param requestParam 用户注册请求DTO
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名 修改用户消息
     * @param requestParam 修改用户参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登入
     * @param requestParam 用户登入请求DTO
     * @return 用户登入返回Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登入
     * @param username 用户名
     * @param token 用户登入 Token
     * @return 用户登入返回true，否则返回false
     */
    Boolean checkLogin(String username, String token);

    /**
     * 用户登出
     * @param username 用户名
     * @param token 用户登入 Token
     */
    void logout(String username, String token);

}
