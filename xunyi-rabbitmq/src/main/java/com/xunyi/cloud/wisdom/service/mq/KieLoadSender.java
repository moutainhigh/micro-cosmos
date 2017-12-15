package com.xunyi.cloud.wisdom.service.mq;

import com.alibaba.fastjson.JSONObject;
import com.xunyi.cloud.wisdom.constants.MQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by thomas.su on 2017/11/10 14:35.
 */
@Component
public class KieLoadSender implements RabbitTemplate.ConfirmCallback {
    private final static Logger logger = LoggerFactory.getLogger(KieLoadSender.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(JSONObject jsonObject) {
        this.rabbitTemplate.convertAndSend(MQConstants.fanoutExchangeName, "", jsonObject);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }
}
