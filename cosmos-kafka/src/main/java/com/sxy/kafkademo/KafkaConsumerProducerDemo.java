package com.sxy.kafkademo;

/**
 * @Author:thomas
 * @Date: 2018/3/15 17:04
 * @Email:1165030287@qq.com
 * @Description:
 */
public class KafkaConsumerProducerDemo {
   public static void main(String[] args){
       KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
       producerThread.start();

       KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
       consumerThread.start();
   }

}
