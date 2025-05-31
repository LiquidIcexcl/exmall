package org.liquidice.exmall.logistics.dao.entity;

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
@TableName("t_logistics")
public class LogisticsDO extends BaseDO {
}
