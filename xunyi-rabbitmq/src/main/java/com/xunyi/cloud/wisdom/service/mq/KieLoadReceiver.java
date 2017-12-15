package com.xunyi.cloud.wisdom.service.mq;

import com.alibaba.fastjson.JSONObject;
import com.xunyi.cloud.wisdom.constants.MQConstants;
import com.xunyi.cloud.wisdom.enums.MQMsgEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by thomas.su on 2017/11/10 14:35.
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(value = MQConstants.fanoutExchangeName, type = ExchangeTypes.FANOUT, durable = "false")))
public class KieLoadReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KieLoadReceiver.class);

    @RabbitHandler
    public void process(JSONObject msg) {
        logger.info("收到的信息为 MQ：msg={}", msg);
        if (msg != null) {
            String code = msg.getString("code");
            String versionId = msg.getString("versionId");
            if (MQMsgEnum.DEPLOY_STRATEGY.getCode().equalsIgnoreCase(code)) {
                //加载流程
                logger.info("加载流程=============================");
            } else if (MQMsgEnum.CLOSE_STRATEGY.getCode().equalsIgnoreCase(code)) {
                //移除流程
                logger.info("移除流程=============================");
            }
        }
    }
}
