/*
package com.cosmos.csp.hbase.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;

*/
/**
 * <p> HBase配置 </p>
 *
 * @author Daniel Lea
 * @date 2018/7/17
 *//*

@Slf4j
@Configuration
@EnableConfigurationProperties(value = HBaseConfig.HBaseProperties.class)
public class HBaseConfig {

    @Data
    @ConfigurationProperties(prefix = "hbase.zookeeper")
    public class HBaseProperties {
        private String quorum;
        private String port;
    }
    */
/**
     * HBase配置
     *//*

    @Bean
    public org.apache.hadoop.conf.Configuration hBaseConfiguration(final HBaseProperties hBaseProperties) {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", hBaseProperties.getQuorum());
        conf.set("hbase.zookeeper.property.clientPort", hBaseProperties.getPort());
        return conf;
    }

    */
/**
     *
     * @param hBaseConfiguration
     * @return
     *//*

    @Bean
    public HbaseTemplate hbaseTemplate(final org.apache.hadoop.conf.Configuration hBaseConfiguration) {
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        hbaseTemplate.setConfiguration(hBaseConfiguration);
        hbaseTemplate.setAutoFlush(false);
        return hbaseTemplate;
    }

    @Bean
    public Admin hBaseAdmin(final org.apache.hadoop.conf.Configuration hBaseConfiguration) {
        try (Connection connection = ConnectionFactory.createConnection(hBaseConfiguration)){
            return connection.getAdmin();
        } catch (IOException e) {
            log.error("[获取HBase管理员] 获取管理员实例失败. e ", e);
            return null;
        }
    }

}
*/
