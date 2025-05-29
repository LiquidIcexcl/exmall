package org.liquidice.exmall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liquidice.exmall.admin.dao.entity.UserWalletDO;
import org.liquidice.exmall.admin.dto.req.UserWalletReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserWalletRespDTO;

public interface UserWalletService extends IService<UserWalletDO> {


    UserWalletRespDTO getUserWalletByUid(Long uid);
}
