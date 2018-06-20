package kafka.demo;

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
 * @Date: 2018/5/8 13:56
 * @Description:
 */
public class TestKafkaConsumer {
    private final ConsumerConnector consumer;
    private final String topic;

    public TestKafkaConsumer(String topic){
        Properties props = new Properties();
        props.put("zookeeper.connect","127.0.0.1:2181");
        props.put("group.id","group1");
        props.put("zookeeper.session.timeout.ms","40000");
        props.put("zookeeper.sync.time.ms","200");
        props.put("auto.commit.interval.ms","1000");
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        this.topic = topic;
    }

    public void consume(){
        Map<String,Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic,new Integer(1));
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();

        System.out.println("=====》消费者收到的消息："+ new String(it.next().message()));
    }


}
