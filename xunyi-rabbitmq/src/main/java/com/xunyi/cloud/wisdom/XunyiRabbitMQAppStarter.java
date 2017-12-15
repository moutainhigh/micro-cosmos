package com.xunyi.cloud.wisdom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * '
 * <p>
 * <p>
 * <p>Title:WhaleWisdomConsoleAppStarter</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author thomas
 * @Date 2017年2月14日 下午2:46:10
 */
@SpringBootApplication(scanBasePackages = "com.xunyi.cloud.wisdom")
public class XunyiRabbitMQAppStarter implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(XunyiRabbitMQAppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(XunyiRabbitMQAppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}

