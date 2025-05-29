package org.liquidice.exmall.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花算法配置类
 */
@Configuration(value = "OrderSnowFlakeConfig")
public class SnowFlakeConfig {
    @Bean
    @ConditionalOnMissingBean
    public SnowFlakeIdGenerator snowFlakeIdGenerator(
            @Value("${snowflake.datacenter-id:1}") Long datacenterId,
            @Value("${snowflake.machine-id:1}") Long machineId
    ) {
        return new SnowFlakeIdGenerator(datacenterId, machineId);
    }
}