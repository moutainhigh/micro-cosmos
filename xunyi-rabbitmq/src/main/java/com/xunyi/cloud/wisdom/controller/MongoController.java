package com.xunyi.cloud.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.model.ReportOverdueConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:thomas
 * @Date: 2018/6/19 11:51
 * @Description:
 */
@RestController
@RequestMapping(value = "/mongo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MongoController {
    private final static Logger logger = LoggerFactory.getLogger(MongoController.class);


    @Autowired
    public MongoTemplate mongoTemplate;

    @RequestMapping(value = "/insertReportConfig", method = RequestMethod.POST)
    public String insertReportConfig(@RequestBody ReportOverdueConfig reportOverdueConfig){
        mongoTemplate.insert(reportOverdueConfig,ReportOverdueConfig.OVERDUE_COLLECTION_NAME);
        logger.info("----------------------reportOverdueConfig:{}", JSON.toJSONString(reportOverdueConfig));
        return "{\"code\":200}";
    }

}
