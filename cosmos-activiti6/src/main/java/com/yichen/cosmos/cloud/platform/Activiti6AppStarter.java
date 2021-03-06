package com.yichen.cosmos.cloud.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by thomas.su on 2018/2/27 9:27.
 */

@ImportResource({"classpath:config/riskcontrol-beans.xml", "classpath:config/riskcontrol-activiti.xml"})
@SpringBootApplication(scanBasePackages = "com.yichen.cosmos.cloud.platform", exclude = {MongoAutoConfiguration.class})
public class Activiti6AppStarter implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(Activiti6AppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(Activiti6AppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}