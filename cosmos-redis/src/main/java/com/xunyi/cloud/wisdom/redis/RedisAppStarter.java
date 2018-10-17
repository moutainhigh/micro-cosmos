package com.xunyi.cloud.wisdom.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @Author:thomas su
 * @Date: 2018/10/9 14:20
 * @Description:
 */

@SpringBootApplication(scanBasePackages = "com.xunyi.cloud.wisdom.activiti", exclude = {MongoAutoConfiguration.class})
public class RedisAppStarter implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(RedisAppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(RedisAppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}