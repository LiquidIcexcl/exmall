package org.liquidice.exmall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.admin.dao.entity.UserDO;
import org.liquidice.exmall.admin.dao.entity.UserShopDO;
import org.liquidice.exmall.admin.dao.mapper.UserShopMapper;
import org.liquidice.exmall.admin.dto.req.UserShopReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserRespDTO;
import org.liquidice.exmall.admin.dto.resp.UserShopRespDTO;
import org.liquidice.exmall.admin.service.UserShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import org.liquidice.exmall.framework.exception.ServiceException;

import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

import static org.liquidice.exmall.admin.common.enums.UserErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
public class UserShopServiceImpl extends ServiceImpl<UserShopMapper, UserShopDO> implements UserShopService {

    @Override
    public UserShopRespDTO getShopByUid(Long uid) {
        LambdaQueryWrapper<UserShopDO> queryWrapper = Wrappers.lambdaQuery(UserShopDO.class)
                .eq(UserShopDO::getUid, uid);
        UserShopDO userShopDO = baseMapper.selectOne(queryWrapper);
        if (userShopDO == null) {
            throw new ServiceException(USER_SHOP_IS_NULL);
        }
        UserShopRespDTO result = new UserShopRespDTO();
        BeanUtils.copyProperties(userShopDO, result);
        return result;
    }

    @Override
    public UserShopRespDTO createShop(Long uid, UserShopReqDTO requestParam) {
        LambdaQueryWrapper<UserShopDO> queryWrapper = Wrappers.lambdaQuery(UserShopDO.class)
                .eq(UserShopDO::getUid, uid);
        UserShopDO userShopDO = baseMapper.selectOne(queryWrapper);
        if (userShopDO != null) {
            throw new ServiceException(USER_SHOP_IS_EXIST);
        }
        userShopDO = new UserShopDO();
        BeanUtils.copyProperties(requestParam, userShopDO);
        userShopDO.setUid(uid);
        boolean save = baseMapper.insert(userShopDO) > 0;
        if (!save) {
            throw new ServiceException("创建失败");
        }
        UserShopRespDTO result = new UserShopRespDTO();
        BeanUtils.copyProperties(userShopDO, result);
        return result;
    }

    @Override
    public void updateShop(Long uid, UserShopReqDTO requestParam) {
        LambdaUpdateWrapper<UserShopDO> updateWrapper = Wrappers.lambdaUpdate(UserShopDO.class)
                .eq(UserShopDO::getUid, uid)
                .eq(UserShopDO::getDelFlag, 0);
        UserShopDO userShopDO = new UserShopDO();
        BeanUtils.copyProperties(requestParam, userShopDO);
        baseMapper.update(userShopDO, updateWrapper);
    }
}
