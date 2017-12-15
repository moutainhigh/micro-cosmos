package com.xunyi.cloud.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.xunyi.cloud.wisdom.enums.MQMsgEnum;
import com.xunyi.cloud.wisdom.service.mq.KieLoadSender;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import com.yichen.cosmos.cloud.platform.util.SUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by thomas.su on 2017/12/15 10:19.
 */
@RestController
@RequestMapping(value = "/mq", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MQController {
    private final static Logger logger = LoggerFactory.getLogger(MQController.class);

    @Autowired
    private KieLoadSender kieLoadSender;

    @RequestMapping(value = "/sendMQMsg", method = RequestMethod.GET)
    public String sendMQMsg(HttpServletRequest request) {
        //MQ 全部服务通知，加载生成流程
        JSONObject mq_buildFlowProcess = new JSONObject();
        mq_buildFlowProcess.put("code", MQMsgEnum.DEPLOY_STRATEGY.getCode());
        String versionId = SUID.getUUID();
        mq_buildFlowProcess.put("versionId", versionId);
        kieLoadSender.send(mq_buildFlowProcess);
        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
                .build().toString();
    }

}
