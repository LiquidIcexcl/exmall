package org.liquidice.exmall.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.exception.ServiceException;
import org.liquidice.exmall.pay.dao.entity.PayDO;
import org.liquidice.exmall.pay.dao.entity.UserWalletDO;
import org.liquidice.exmall.pay.dao.mapper.PayMapper;
import org.liquidice.exmall.pay.dao.mapper.UserWalletMapper;
import org.liquidice.exmall.pay.dto.req.PayReqDTO;
import org.liquidice.exmall.pay.dto.resp.PayRespDTO;
import org.liquidice.exmall.pay.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl extends ServiceImpl<PayMapper, PayDO> implements PayService {

    private final PayMapper baseMapper;
    private final UserWalletMapper userMapper;

    public void PayHandler(PayDO payDO) {
        if(payDO == null) {
            throw new ServiceException("支付记录不能为空");
        }
        if (payDO.getPayType() == null || payDO.getPayType().isEmpty()) {
            throw new ServiceException("支付类型不能为空");
        }
        if (payDO.getPayerUid() == null || payDO.getPayeeUid() == null) {
            throw new ServiceException("支付双方的用户ID不能为空");
        }

        if (payDO.getPayType().equals("outcome")) {
            // 扣除付款方钱包余额
            LambdaQueryWrapper<UserWalletDO> queryWrapper = Wrappers.lambdaQuery(UserWalletDO.class)
                    .eq(UserWalletDO::getUid, payDO.getPayerUid())
                    .eq(UserWalletDO::getDelFlag, 0);
            UserWalletDO userWallet = userMapper.selectOne(queryWrapper);
            if (userWallet == null) {
                throw new ServiceException("找不到付款方的用户钱包记录");
            }
            if (userWallet.getBalance() < payDO.getPayAmount()) {
                throw new ServiceException("付款方钱包余额不足");
            }
            // 更新付款方钱包余额
            userWallet.setBalance(userWallet.getBalance() - payDO.getPayAmount());
            userMapper.updateById(userWallet);
        }
        else if (payDO.getPayType().equals("income")) {
            // 增加收款方钱包余额
            LambdaQueryWrapper<UserWalletDO> queryWrapper = Wrappers.lambdaQuery(UserWalletDO.class)
                    .eq(UserWalletDO::getUid, payDO.getPayeeUid())
                    .eq(UserWalletDO::getDelFlag, 0);
            UserWalletDO userWallet = userMapper.selectOne(queryWrapper);
            if (userWallet == null) {
                throw new ServiceException("找不到收款方的用户钱包记录");
            }
            // 更新收款方钱包余额
            userWallet.setBalance(userWallet.getBalance() + payDO.getPayAmount());
            userMapper.updateById(userWallet);
        } else {
            throw new ServiceException("未知的支付类型: " + payDO.getPayType());
        }
    }

    @Override
    public PayRespDTO getPayByPayId(Long payId) {
        LambdaQueryWrapper<PayDO> queryWrapper = new LambdaQueryWrapper<PayDO>()
                .eq(PayDO::getPayId, payId)
                .eq(PayDO::getDelFlag, 0);
        PayDO payDO = baseMapper.selectOne(queryWrapper);
        if (payDO == null) {
            throw new ServiceException("找不到支付ID为 " + payId + " 的支付记录");
        }
        PayRespDTO payRespDTO = new PayRespDTO();
        BeanUtils.copyProperties(payDO, payRespDTO);
        return payRespDTO;
    }

    @Override
    public List<PayRespDTO> getPayByUid(Long uid) {
        List<PayRespDTO> result = new ArrayList<>();
        LambdaQueryWrapper<PayDO> queryWrapperPayer = new LambdaQueryWrapper<PayDO>()
                .eq(PayDO::getPayType, "outcome")
                .eq(PayDO::getPayerUid, uid)
                .eq(PayDO::getDelFlag, 0);
        List<PayDO> payDOPayerList = baseMapper.selectList(queryWrapperPayer);
        for (PayDO payDOPayer : payDOPayerList) {
            PayRespDTO payRespDTO = new PayRespDTO();
            BeanUtils.copyProperties(payDOPayer, payRespDTO);
            result.add(payRespDTO);
        }

        LambdaQueryWrapper<PayDO> queryWrapperPayee = new LambdaQueryWrapper<PayDO>()
                .eq(PayDO::getPayType, "income")
                .eq(PayDO::getPayeeUid, uid)
                .eq(PayDO::getDelFlag, 0);
        List<PayDO> payDOPayeeList = baseMapper.selectList(queryWrapperPayee);
        for (PayDO payDOPayee : payDOPayeeList) {
            PayRespDTO payRespDTO = new PayRespDTO();
            BeanUtils.copyProperties(payDOPayee, payRespDTO);
            result.add(payRespDTO);
        }

        if (result.isEmpty()) {
            throw new ServiceException("找不到用户ID为 " + uid + " 的支付记录");
        }
        return result;
    }

    @Override
    public PayRespDTO getPayByOrderId(Long orderId, Long uid) {
        LambdaQueryWrapper<PayDO> queryWrapper = new LambdaQueryWrapper<PayDO>()
                .eq(PayDO::getPayType, "outcome")
                .eq(PayDO::getPayerUid, uid)
                .eq(PayDO::getOrderId, orderId)
                .eq(PayDO::getDelFlag, 0);
        PayDO payDO = baseMapper.selectOne(queryWrapper);
        if (payDO != null) {
            PayRespDTO payRespDTO = new PayRespDTO();
            BeanUtils.copyProperties(payDO, payRespDTO);
            return payRespDTO;
        }

        LambdaQueryWrapper<PayDO> queryWrapperIncome = new LambdaQueryWrapper<PayDO>()
                .eq(PayDO::getPayType, "income")
                .eq(PayDO::getPayeeUid, uid)
                .eq(PayDO::getOrderId, orderId)
                .eq(PayDO::getDelFlag, 0);
        PayDO payDOIncome = baseMapper.selectOne(queryWrapperIncome);
        if (payDOIncome != null) {
            PayRespDTO payRespDTO = new PayRespDTO();
            BeanUtils.copyProperties(payDOIncome, payRespDTO);
            return payRespDTO;
        }
        throw new ServiceException("找不到订单ID为 " + orderId + " 的支付记录");
    }

    @Override
    public Boolean createPay(PayReqDTO requestParam) {
        PayDO payDO = new PayDO();
        BeanUtils.copyProperties(requestParam, payDO);
        int inserted = baseMapper.insert(payDO);
        if (inserted < 1) {
            throw new ServiceException("支付记录保存失败");
        }
        // 处理支付记录
        PayHandler(payDO);

        if (requestParam.getPayType().equals("outcome")) {
           payDO.setPayType("income");
           int insertedIncome = baseMapper.insert(payDO);
           if (insertedIncome < 1) {
              throw new ServiceException("支付记录保存失败");
           }
           PayHandler(payDO);
        }
        return true;
    }

    @Override
    public Boolean createWalletRecharge(PayReqDTO requestParam) {
        return null;
    }
}
