package org.liquidice.exmall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.admin.dao.entity.UserShopDO;
import org.liquidice.exmall.admin.dto.req.UserShopReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserRespDTO;
import org.liquidice.exmall.admin.dto.resp.UserShopRespDTO;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;

public interface UserShopService extends IService<UserShopDO> {

    /**
     * 根据用户ID获取商户信息
     * @param uid 用户ID
     * @return 商户信息
     */
    UserShopRespDTO getShopByUid(Long uid);

    /**
     * 用户创建商户
     * @param uid 用户ID
     * @param requestParam 商户信息
     * @return 商户信息
     */
    UserShopRespDTO createShop(UserShopReqDTO requestParam);

    /**
     * 更新商户信息
     * @param uid 用户ID
     * @param requestParam 商户信息
     */
    void updateShop(UserShopReqDTO requestParam);
}
