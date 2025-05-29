package org.liquidice.exmall.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.pay.dao.entity.payDO;
import org.liquidice.exmall.pay.dao.mapper.payMapper;
import org.liquidice.exmall.pay.service.payService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class payServiceImpl extends ServiceImpl<payMapper, payDO> implements payService {
}
