package org.liquidice.exmall.pay.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liquidice.exmall.framework.database.BaseDO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_pay")
public class payDO extends BaseDO {
}
