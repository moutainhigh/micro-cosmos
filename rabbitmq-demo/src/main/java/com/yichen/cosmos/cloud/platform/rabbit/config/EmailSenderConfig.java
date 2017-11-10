package com.yichen.cosmos.cloud.platform.rabbit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * 配置邮件服务相关的配置信息
 * 邮件服务器，SSL端口，发件方邮箱，发件方密码，超时时间
 *
 * @author hzxab
 * @date 2016-7-20
 */
@Configuration
@PropertySource("classpath:/email.properties")
@MapperScan(" com.yichen.cosmos.cloud.platform.rabbit")
public class EmailSenderConfig {

    @Value("${mail.smtp.host}")
    private String host; //邮箱服务器
    @Value("${mail.smtp.protocol}")
    private String protocol; //邮箱端口
    @Value("${mail.smtp.port}")
    private String port; //SSL 端口号
    @Value("${mail.smtp.user}")
    private String user; //发件人邮箱  默认一个
    @Value("${mail.smtp.password}")
    private String password; //发件人邮箱密码
    @Value("${mail.smtp.auth}")
    private Boolean auth; //是否需要通过验证
    @Value("${mail.smtp.title}")
    private String title;

    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", port);
        p.put("mail.smtp.auth", auth);
        return p;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
