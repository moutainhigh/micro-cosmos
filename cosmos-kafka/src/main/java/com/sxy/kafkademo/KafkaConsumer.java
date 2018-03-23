package com.sxy.kafkademo;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author:thomas
 * @Date: 2018/3/15 16:40
 * @Email:1165030287@qq.com
 * @Description:
 */
public class KafkaConsumer extends  Thread{
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String topic){
        consumer = Consumer.createJavaConsumerConnector(createCosumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createCosumerConfig(){
        Properties pros = new Properties();
        pros.put("zookeeper.connect",KafkaProperties.zkConnect);
        pros.put("group.id",KafkaProperties.groupId);
        pros.put("zookeeper.session.timeout.ms","40000");
        pros.put("zookeeper.sync.time.ms","200");
        pros.put("auto.commit.interval.ms","1000");
        return new ConsumerConfig(pros);
    }

    @Override
    public void run() {
        Map<String,Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic,new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            logger.info("receive：" + new String(it.next().message()));
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
