package com.yichen.cosmos.cloud.platform.controller;

/**
 * @Author:thomas
 * @Date: 2018/6/28 16:29
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.constants.CommonConstants;
import com.yichen.cosmos.cloud.platform.service.TestService001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivitiDroolsDemoController {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiDroolsDemoController.class);


    @Autowired
    private TestService001 testService001;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String reset(){
        CommonConstants.oneTime.set(false);
        return "{\"result\": "+ CommonConstants.oneTime.get() +"}";
    }
    /**
     *
     * 1. 先动态生成流程定义，进行部署
     * 2. 启动一个流程实例，任务是UserTask
     * 3. 继续执行任务
     *
     * @param strategyname
     * @return
     */
    //根据策略名称，动态生成流程定义文件
    @RequestMapping(value = "/createDeployment", method = RequestMethod.POST)
    public String createDeployment(@RequestParam("strategyname") String strategyname){
        testService001.createDeployment(strategyname);
        return "{\"code\":\"200\"}";
    }

    //根据策略名称启用一个流程实例----》
    @RequestMapping(value = "/startProcessByKey", method = RequestMethod.POST)
    public String startProcessByKey(@RequestParam("strategyname") String strategyname,@RequestParam("active") String active){
        Map<String,String> dataMap = testService001.startProcessByKey(strategyname,active);

        return JSON.toJSONString(dataMap);
    }

    //继续执行流程实例中的任务
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    public String completeTask(@RequestParam("taskId") String taskId,@RequestParam("active") String active){
        Map<String,Object> params = new HashMap<>();
        Map result = testService001.completeTask(taskId,params,active);
        return JSON.toJSONString(result);
    }

}
