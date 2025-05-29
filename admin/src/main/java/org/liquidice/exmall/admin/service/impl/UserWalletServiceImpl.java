package org.liquidice.exmall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.admin.dao.entity.UserWalletDO;
import org.liquidice.exmall.admin.dao.mapper.UserWalletMapper;
import org.liquidice.exmall.admin.dto.resp.UserWalletRespDTO;
import org.liquidice.exmall.admin.service.UserWalletService;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWalletServiceImpl extends ServiceImpl<UserWalletMapper, UserWalletDO> implements UserWalletService {


    @Override
    public UserWalletRespDTO getUserWalletByUid(Long uid) {
        LambdaQueryWrapper <UserWalletDO> queryWrapper = Wrappers.<UserWalletDO>lambdaQuery()
                .eq(UserWalletDO::getUid, uid)
                .eq(UserWalletDO::getDelFlag, 0);
        UserWalletDO userWalletDO = baseMapper.selectOne(queryWrapper);
        if (userWalletDO == null) {
            throw new ServiceException("用户钱包不存在");
        }
        UserWalletRespDTO userWalletRespDTO = new UserWalletRespDTO();
        BeanUtils.copyProperties(userWalletDO, userWalletRespDTO);
        return userWalletRespDTO;
    }
}
