package com.xunyi.cloud.wisdom.kaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * Created by thomas on 2018/2/8 14:19.
 */
@SpringBootApplication(scanBasePackages = "com.xunyi.cloud.wisdom.kaf")
public class KafkaAppStarter implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}

