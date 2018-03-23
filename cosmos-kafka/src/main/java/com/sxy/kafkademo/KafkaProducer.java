package com.sxy.kafkademo;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by thomas on 2018/3/15 16:29.
 */
public class KafkaProducer extends Thread{

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final Producer<Integer,String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic){
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "10.200.153.10:9092");
        props.put("broker.list", "10.200.153.10:9092");
        producer = new Producer<>(new ProducerConfig(props));

        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while(true){
            String messageStr = new String("Message_"+messageNo);
            logger.info("Send:"+messageStr);
            producer.send(new KeyedMessage<Integer, String>(topic,messageStr));
            messageNo++;

            try{
                sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}



