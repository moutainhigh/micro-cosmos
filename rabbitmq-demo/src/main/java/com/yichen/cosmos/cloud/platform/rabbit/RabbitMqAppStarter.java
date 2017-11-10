package com.yichen.cosmos.cloud.platform.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

@SpringBootApplication(scanBasePackages = "com.yichen.cosmos.cloud.platform.rabbit")
public class RabbitMqAppStarter implements EmbeddedServletContainerCustomizer, CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqAppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqAppStarter.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        if (container instanceof TomcatEmbeddedServletContainerFactory) {
            logger.info("tomcat 初始化 <WhaleWisdomAppStarter>-------");
//			TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//			tomcat.addConnectorCustomizers(
//					(connector) -> {
//						connector.setMaxPostSize(100000000);
//					}
//			);
        }
        container.setPort(11001);
    }
}
