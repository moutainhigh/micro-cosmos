package com.yichen.cosmos.cloud.platform.rabbit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * credit_app_api默认配置
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月8日 下午2:04:39
 */
@Component
@Configuration
@PropertySource(value = "classpath:/whale_wisdom_default.properties")
public class WhaleWisdomDefultConfig {

}
