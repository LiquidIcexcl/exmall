package org.liquidice.exmall.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花算法配置类
 */
@Configuration
public class SnowFlakeConfig {
    @Bean
    public SnowFlakeIdGenerator snowFlakeIdGenerator(
            @Value("${snowflake.datacenter-id:1}") Long datacenterId,
            @Value("${snowflake.machine-id:1}") Long machineId
    ) {
        return new SnowFlakeIdGenerator(datacenterId, machineId);
    }
}