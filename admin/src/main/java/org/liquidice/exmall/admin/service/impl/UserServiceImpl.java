package org.liquidice.exmall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.liquidice.exmall.admin.common.biz.user.UserContext;
import org.liquidice.exmall.admin.common.biz.user.UserInfoDTO;
import org.liquidice.exmall.admin.common.enums.UserErrorCodeEnum;
import org.liquidice.exmall.admin.config.SnowFlakeIdGenerator;
import org.liquidice.exmall.admin.config.UserConfiguration;
import org.liquidice.exmall.admin.dao.entity.UserConfigurationDO;
import org.liquidice.exmall.admin.dao.entity.UserDO;
import org.liquidice.exmall.admin.dao.entity.UserWalletDO;
import org.liquidice.exmall.admin.dao.mapper.UserConfigurationMapper;
import org.liquidice.exmall.admin.dao.mapper.UserMapper;
import org.liquidice.exmall.admin.dao.mapper.UserWalletMapper;
import org.liquidice.exmall.admin.dto.req.UserLoginReqDTO;
import org.liquidice.exmall.admin.dto.req.UserRegisterReqDTO;
import org.liquidice.exmall.admin.dto.req.UserUpdateReqDTO;
import org.liquidice.exmall.admin.dto.resp.UserLoginRespDTO;
import org.liquidice.exmall.admin.dto.resp.UserRespDTO;
import org.liquidice.exmall.admin.service.UserService;
import org.liquidice.exmall.framework.exception.ClientException;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.liquidice.exmall.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static org.liquidice.exmall.admin.common.constant.RedisCacheConstant.USER_LOGIN_KEY;
import static org.liquidice.exmall.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 用户接口层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final SnowFlakeIdGenerator idGenerator;

    private final UserConfigurationMapper userConfigurationMapper;
    private final UserWalletMapper userWalletMapper;

    public void userConfigurationHandler(Long uid) {
        UserWalletDO userWalletDO = UserWalletDO.builder()
                .uid(uid)
                .balance(0D)
                .build();
        int walletInserted = userWalletMapper.insert(userWalletDO);

        UserConfigurationDO userConfiguration = UserConfigurationDO.builder()
                .uid(uid)
                .build();
        int configInserted = userConfigurationMapper.insert(userConfiguration);

    }

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        if (!lock.tryLock()) {
            throw new ClientException(USER_NAME_EXIST);
        }
        try {
            Long userId = idGenerator.nextId();
            requestParam.setUid(userId);
            int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
            if (inserted < 1) {
                throw new ClientException(USER_SAVE_ERROR);
            }
            userConfigurationHandler(userId);
            userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
        } catch (DuplicateKeyException ex) {
            throw new ClientException(USER_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        if (!Objects.equals(requestParam.getUsername(), UserContext.getUsername())) {
            throw new ClientException("当前登录用户修改请求异常"+UserContext.getUsername());
        }
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户不存在");
        }
        Map<Object, Object> hasLoginMap = stringRedisTemplate.opsForHash().entries(USER_LOGIN_KEY + requestParam.getUsername());
        if (CollUtil.isNotEmpty(hasLoginMap)) {
            stringRedisTemplate.expire(USER_LOGIN_KEY + requestParam.getUsername(), 30L, TimeUnit.MINUTES);
            String token = hasLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException("用户登录错误"));
            UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                    .userId(userDO.getUid().toString())
                    .username(userDO.getUsername())
                    .build();
            UserContext.setUser(userInfoDTO);
            return new UserLoginRespDTO(token);
        }

        /* *
         * Hash
         * Key：login_用户名
         * Value：
         *  Key：token标识
         *  Val：JSON 字符串（用户信息）
         */
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put(USER_LOGIN_KEY + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire(USER_LOGIN_KEY + requestParam.getUsername(), 30L, TimeUnit.MINUTES);
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .userId(userDO.getUid().toString())
                .username(userDO.getUsername())
                .build();
        UserContext.setUser(userInfoDTO);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get(USER_LOGIN_KEY + username, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete(USER_LOGIN_KEY + username);
            return;
        }
        throw new ClientException("用户Token不存在或用户未登录");
    }

    @Override
    public Boolean userIsMerchant(Long uid) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUid, uid);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(USER_NULL);
        }
        Integer result = userDO.getIsMerchant();
        return result != null && result == 1;
    }

    @Override
    public UserRespDTO becomeMerchant(Long uid) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUid, uid);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(USER_NULL);
        }
        if (userDO.getIsMerchant() != null && userDO.getIsMerchant() == 1) {
            throw new ServiceException(USER_IS_MERCHANT);
        }

        userDO.setIsMerchant(1);
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}