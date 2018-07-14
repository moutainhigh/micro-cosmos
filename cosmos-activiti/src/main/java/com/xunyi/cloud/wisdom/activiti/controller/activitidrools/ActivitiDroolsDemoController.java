package com.xunyi.cloud.wisdom.activiti.controller.activitidrools;

/**
 * @Author:thomas
 * @Date: 2018/6/28 16:29
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.*;
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


    //根据策略名称启用一个流程实例----》
    //带有BusinessRuleTask ，且绑定drools rules的流程
    @RequestMapping(value = "/startProcessByKeyOfDrools", method = RequestMethod.POST)
    public String startProcessByKeyOfDrools(@RequestParam("strategyname") String strategyname,@RequestParam("active") String active){
        Map<String,String> dataMap = testService001.startProcessByKeyOfDrools(strategyname,active);

        return JSON.toJSONString(dataMap);
    }


    //继续执行流程实例中的任务
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    public String completeTask(@RequestParam("taskId") String taskId,@RequestParam("active") String active){
        Map<String,Object> params = new HashMap<>();
        Map result = testService001.completeTask(taskId,params,active);
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

    @Autowired
    private TestBusinessRuleTaskService2 testBusinessRuleTaskService2;


    //ruleflow
    //测试独占执行，只是在规则中添加属性 activation-group
    //因为规则节点绑定了规则名称，相当于规则组了 ruleflow-group
    @RequestMapping(value = "/createTestBusinessRuleTaskFlow2", method = RequestMethod.POST)
    public String createTestBusinessRuleTaskFlow2(@RequestParam("strategyname") String strategyname){
        testBusinessRuleTaskService2.createDeployment(strategyname);
        logger.info("测试规则节点.request....strategyname：{}",strategyname);
        return "{\"code\":\"200\"}";
    }


    @Autowired
    private TestBusinessRuleTaskService3 testBusinessRuleTaskService3;



//    * 测试：
//            *  1. 测试drools引入java Map等类，为什么规则没有触发执行？？ 在TestBusinessRuleTaskService2 试过
//    *  2. 多个规则节点 BusinessRuleTask ，多服务分布式执行时，彼此间规则执行的结果 update(map);后，
//            *  值是否会传递？（即：第一个规则节点的规则决策，作为第二个规则节点的规则条件）
    @RequestMapping(value = "/createTestBusinessRuleTaskFlow3", method = RequestMethod.POST)
    public String createTestBusinessRuleTaskFlow3(@RequestParam("strategyname") String strategyname){
        testBusinessRuleTaskService3.createDeployment(strategyname);
        logger.info("测试规则节点.request....strategyname：{}",strategyname);
        return "{\"code\":\"200\"}";
    }


    //测试线条件
    //1. 线条件中的变量是否与BusinessRuleTask的drools的变量共用(是否需要重新添加到variables中到task执行中)
    // 不可以；  "org.activiti.engine.ActivitiException",
    //"Unknown property used in expression: ${result=='pass'}"
//    Caused by: org.activiti.engine.impl.javax.el.PropertyNotFoundException: Cannot resolve identifier 'result'
    //org.activiti.engine.impl.juel.AstEval


//    taskService.complete(taskId,params);
//    线条件、提前终止流程、线监听器、任务决策值线条件可以复用
    @Autowired
    private TestSequenceFlowService testSequenceFlowService;
    @RequestMapping(value = "/createSequenceFlowTest", method = RequestMethod.POST)
    public String createSequenceFlowTest(@RequestParam("strategyname") String strategyname){
        testSequenceFlowService.createDeployment(strategyname);
        logger.info("测试规则节点.request....strategyname：{}",strategyname);
        return "{\"code\":\"200\"}";
    }



}
