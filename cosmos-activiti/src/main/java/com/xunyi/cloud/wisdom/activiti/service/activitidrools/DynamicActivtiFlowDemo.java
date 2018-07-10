package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.zero.BuilderComplexFlowProcessService;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.kie.internal.KnowledgeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:thomas
 * @Date: 2018/6/27 18:39
 * @Description:
 * 动态生成工作流
 */
@Service
public class DynamicActivtiFlowDemo extends BaseService implements IDynamicActivtiFlowDemo{
    private static final Logger logger = LoggerFactory.getLogger(DynamicActivtiFlowDemo.class);

    @Autowired
    private BuilderComplexFlowProcessService builderComplexFlowProcessService;

    @Override
    public void createActivitiFlow() {
        //1.创建流程
        Process process = new Process();
        process.setId("my-process");

        StartEvent startEvent = ComponentPool.startEvent();
        String startId = startEvent.getId();
        process.addFlowElement(startEvent);

        EndEvent endEvent = ComponentPool.endEvent();
        String endId = endEvent.getId();
        process.addFlowElement(endEvent);

        String[] ruleNames = new String[]{"bpm-flowgroup_1", "bpm-flowgroup_2"};
        BusinessRuleTask businessRuleTask = ComponentPool.businessRuleTask(Arrays.asList(ruleNames));
        String busId = businessRuleTask.getId();
        process.addFlowElement(businessRuleTask);

        process.addFlowElement(ComponentPool.sequenceFlow(startId, busId));
        process.addFlowElement(ComponentPool.sequenceFlow(busId, endId));


        //2. model
        BpmnModel model = new BpmnModel();
        model.addProcess(process);

        //3. 布局
        new BpmnAutoLayout(model).execute();

        logger.info("model:{}", JSON.toJSONString(model));
        //资源名称
        String resourceName = "dynamic.bpmn";
        //4. 部署
        Deployment deployment = repositoryService.createDeployment().addBpmnModel(resourceName, model).name("Dynamic process deployment").deploy();
        if (deployment != null) {
            //加载关联规则
            KnowledgeBase knowledgeBase = builderComplexFlowProcessService.buildComplexFlowProcess(ruleContextList());

            //参考RulesDeployer 提取
            DeploymentManager deploymentManager = Context
                    .getProcessEngineConfiguration()
                    .getDeploymentManager();

            //关联知识仓库及部署
            //缓存是单机的，不是分布式的，需要自行实现
//            https://blog.csdn.net/silent_zqy/article/details/70148298
            deploymentManager.getKnowledgeBaseCache().add(deployment.getId(), knowledgeBase);
            //-----------------------------------------------------------------------------

            //获取流程定义
            String deplyId = deployment.getId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deplyId).singleResult();
            String processDefinitionId = processDefinition.getId();
            Map<String, Object> variables = new HashMap<>();
            variables.put("applyUser", "empoyee");
            variables.put("days", "3");

            if (processDefinition.isSuspended()) {
                repositoryService.activateProcessDefinitionById(processDefinitionId);
            }

            //全局业务key（如：以一次请求requestId），保证业务key与processInstance相关联
//            https://blog.csdn.net/songzheng_741/article/details/17199339
            String businessKey = "globalBusinessKey";
            //获取流程实例
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);

            processInstance.isSuspended();
            processInstance.isEnded();

            // 5. Check if task is available
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getId()).list();

            logger.info("task size:{}", tasks.size());

            //-------------------
        }
    }



    public static List<Map<String, String>> ruleContextList(){
        List<Map<String, String>> ruleContextList = new ArrayList<>();
        Map<String,String> rule1Map = new HashMap<>();
        Map<String,String> rule2Map = new HashMap<>();

        String rule1 =  "package bpm;\n" +
                "import java.util.Map;" +
                "rule \"bpm-flowgroup_1\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 60\n" +
                "ruleflow-group \"test1\" \n" +
                "activation-group \"activation-group1\"\n" +
                "    when\n" +
                "        eval(true)\n" +
                "        map:Map()\n" +
                "    then\n" +
                "        System.out.println(\"flowgroup_2\");\n" +
                "        System.out.println(drools.getRule());\n" +
                "        System.out.println(\"------------222-------------------\");\n" +
                "      \tSystem.out.println(map);\n" +
                "        System.out.println(\"-------------222------------------\");\n" +
                "        map.put(\"age\",\"20\");\n" +
                "        update(map);\n" +
                "        \n" +
                "end";

        rule1Map.put("drlContext",rule1);
        String rule2 = "package bpm;\n" +
                "import java.util.Map;" +
                "rule \"bpm-flowgroup_2\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 59\n" +
                "ruleflow-group \"test1\" \n" +
                "activation-group \"activation-group1\"\n" +
                "    when\n" +
                "        eval(true)\n" +
                "        map:Map()\n" +
                "    then\n" +
                "        System.out.println(\"flowgroup_2\");\n" +
                "        System.out.println(drools.getRule());\n" +
                "        System.out.println(\"------------222-------------------\");\n" +
                "      \tSystem.out.println(map);\n" +
                "        System.out.println(\"-------------222------------------\");\n" +
                "        map.put(\"age\",\"20\");\n" +
                "        update(map);\n" +
                "        \n" +
                "end";
        rule2Map.put("drlContext",rule2);

        return ruleContextList;
    }
}
