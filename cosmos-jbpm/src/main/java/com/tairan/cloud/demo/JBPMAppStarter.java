package com.tairan.cloud.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 *
 */
@SpringBootApplication(scanBasePackages = "com.tairan.cloud.demo", exclude = {MongoAutoConfiguration.class})
public class JBPMAppStarter implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(JBPMAppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(JBPMAppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}