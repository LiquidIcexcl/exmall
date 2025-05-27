package org.liquidice.exmall.admin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户流量风控配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix="exmall.flow-limit")
public class UserFlowRiskControlConfiguration {

    /**
     * 是否开启用户流量风控验证
     */
    private Boolean enable;

    /**
     * 流量防空时间窗口
     */
    private String timeWindow;

    /**
     * 流量风控时间窗口内可访问次数
     */
    private Long maxAccessCount;
}
