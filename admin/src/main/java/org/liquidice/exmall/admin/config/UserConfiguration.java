package org.liquidice.exmall.admin.config;

import org.liquidice.exmall.admin.common.biz.user.UserFlowRiskControlFilter;
import org.liquidice.exmall.admin.common.biz.user.UserTransmitFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 用户配置自动装配
 */
@Configuration
public class UserConfiguration {

    /**
     * 用户信息传递过滤器
     */
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(StringRedisTemplate stringRedisTemplate) {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(0);
        return registration;
    }

    /**
     * 用户信操作流量风控过滤器
     */
    @Bean
    @ConditionalOnProperty(name="exmall.flow-limit.enable", havingValue="true")
    public FilterRegistrationBean<UserFlowRiskControlFilter> globalUserFlowRiskControlFilter(
            StringRedisTemplate stringRedisTemplate,
            UserFlowRiskControlConfiguration userFlowRiskControlConfiguration){
            FilterRegistrationBean<UserFlowRiskControlFilter> registration = new FilterRegistrationBean<>();
            registration.setFilter(new UserFlowRiskControlFilter(stringRedisTemplate, userFlowRiskControlConfiguration));
            registration.addUrlPatterns("/*");
            registration.setOrder(10);
            return registration;
    }


}
