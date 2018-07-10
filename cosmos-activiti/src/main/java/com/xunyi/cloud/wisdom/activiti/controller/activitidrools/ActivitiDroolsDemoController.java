package com.xunyi.cloud.wisdom.activiti.controller.activitidrools;

/**
 * @Author:thomas
 * @Date: 2018/6/28 16:29
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.DynamicAddLisnterService;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.IDynamicActivtiFlowDemo;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.TestBusinessRuleTaskService;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.TestService001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivitiDroolsDemoController {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiDroolsDemoController.class);

    @Autowired
    private IDynamicActivtiFlowDemo dynamicActivtiFlowDemo;


    @RequestMapping(value = "/testdemo", method = RequestMethod.GET)
    public String testdemo(){
        dynamicActivtiFlowDemo.createActivitiFlow();
        return "ok";
    }

    @Autowired
    private IDynamicActivtiFlowDemo dynamicFlow_180705;

    @RequestMapping(value = "/dynamicFlow_180705", method = RequestMethod.GET)
    public String dynamicFlow_180705(){
        dynamicFlow_180705.createActivitiFlow();
        return "{\"code\":\"200\"}";
    }

    @Autowired
    private TestService001 testService001;

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
        Map result = testService001.completeTask(taskId,null,active);
        return JSON.toJSONString(result);
    }


    @Autowired
    private DynamicAddLisnterService dynamicAddLisnterService;

    /**
     * 测试动态添加监听器
     * @param strategyname
     * @return
     */
    //dynamic_loan
    @RequestMapping(value = "/createDynamicAddLisnterDeployment", method = RequestMethod.POST)
    public String createDynamicAddLisnterDeployment(@RequestParam("strategyname") String strategyname){
        dynamicAddLisnterService.createDeployment(strategyname);
        logger.info("测试动态添加监听器……  测试规则");
        return "{\"code\":\"200\"}";
    }

    /**
     * 创建规则节点流程
     * 测试规则执行
     * @param strategyname
     * @return
     */

    @Autowired
    private TestBusinessRuleTaskService testBusinessRuleTaskService;

    //ruleflow
    @RequestMapping(value = "/createTestBusinessRuleTaskFlow", method = RequestMethod.POST)
    public String createTestBusinessRuleTaskFlow(@RequestParam("strategyname") String strategyname){
        testBusinessRuleTaskService.createDeployment(strategyname);
        logger.info("测试规则节点.request....strategyname：{}",strategyname);
        return "{\"code\":\"200\"}";
    }
}
