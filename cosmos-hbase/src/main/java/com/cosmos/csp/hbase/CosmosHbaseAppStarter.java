package com.cosmos.csp.hbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @Author:thomas su
 * @Date: 2018/9/22 12:43
 * @Description:
 */
//@Import(FdfsClientConfig.class)
@SpringBootApplication(scanBasePackages = "com.cosmos.csp.hbase", exclude = {MongoAutoConfiguration.class})
public class CosmosHbaseAppStarter implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(CosmosHbaseAppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(CosmosHbaseAppStarter.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

    }
}
