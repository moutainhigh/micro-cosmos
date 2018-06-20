package kafka.demo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * @Author:thomas
 * @Date: 2018/5/8 13:55
 * @Description:
 */
public class TestKafkaProducer {

    private final String topic;
    private final Producer<Integer,String> producer;
    private final Properties props = new Properties();

    public TestKafkaProducer(String topic){
        props.put("serializer.class","kafka.serializer.StringEncoder");
        props.put("metadata.broker.list","127.0.0.1:9092");
        props.put("broker.list","127.0.0.1:9092");
        producer = new Producer<Integer, String>(new ProducerConfig(props));

        this.topic = topic;
    }

    public void produce(){
        int messageno = 1;
        while(true){
            String message = new String("message_"+messageno);
            producer.send(new KeyedMessage<Integer,String>(topic,message));
            messageno ++;
        }
    }

}
