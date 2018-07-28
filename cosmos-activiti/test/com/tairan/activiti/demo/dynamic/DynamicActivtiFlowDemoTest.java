package com.tairan.activiti.demo.dynamic;

import com.alibaba.fastjson.JSON;
import com.tairan.activiti.demo.dynamic.listeners.TestGlobalEventListener;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.ComponentPool;
import com.xunyi.cloud.wisdom.activiti.zero.BuilderComplexFlowProcessService;
import com.yichen.cosmos.cloud.platform.util.SUID;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityManager;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.engine.impl.persistence.entity.ResourceEntityManager;
import org.activiti.engine.impl.rules.RulesDeployer;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.conf.MaxThreadsOption;
import org.drools.impl.adapters.KnowledgeBaseConfigurationAdapter;
import org.drools.io.ResourceFactory;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author:thomas
 * @Date: 2018/6/27 18:39
 * @Description:
 * 动态生成工作流
 * https://blog.csdn.net/tjl379678792/article/details/71750864
 */

public class DynamicActivtiFlowDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(DynamicActivtiFlowDemoTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Autowired
    private BuilderComplexFlowProcessService builderComplexFlowProcessService;

    public static final String businessKey = "globalBusinessKey";


    @Test
    public void test_001(){
        System.out.println(TestGlobalEventListener.class.getName());
    }
    @Test
    public void createActivitiFlow() {
        //1.创建流程
        Process process = new Process();
        process.setId("my-process");//ID 一定要指定，不然会构建出错

//        https://blog.csdn.net/iflow/article/details/9834705

        List<String> lisenerList = new ArrayList<String>();
//        lisenerList.add(TestGlobalEventListener.class.getName());
        lisenerList.add("com.tairan.activiti.demo.dynamic.listeners.TestGlobalEventListener");
        List<ActivitiListener> list = new ArrayList<>();
        for (String taskListener : lisenerList) {
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent(TaskListener.EVENTNAME_ALL_EVENTS);
            //1. Spring配置以变量形式调用写入
            //2. 通过继承TaskListener方法（其它方法可参考 ImplementationType）
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);

            listener.setImplementation(taskListener);
            list.add(listener);
        }
//        listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
//        listener.setImplementation("${test}");
//        process.setExecutionListeners(list);

//        ActivitiMonitoringEventType
        //--------------------------------------

        StartEvent startEvent = ComponentPool.startEvent();
        String startId = startEvent.getId();
        process.addFlowElement(startEvent);

        EndEvent endEvent = ComponentPool.endEvent();
        String endId = endEvent.getId();
        process.addFlowElement(endEvent);

        String[] ruleNames = new String[]{"bpm-flowgroup_1", "bpm-flowgroup_2"};
        BusinessRuleTask businessRuleTask = ComponentPool.businessRuleTask(Arrays.asList(ruleNames));
        businessRuleTask.setExecutionListeners(list);
        String busId = businessRuleTask.getId();
        process.addFlowElement(businessRuleTask);


//        ServiceTask serviceTask = ComponentPool.serviceTask();
//        String sid =serviceTask.getId();
//        process.addFlowElement(serviceTask);

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
        Deployment deployment = activitiRule.getRepositoryService().createDeployment()

                .addBpmnModel(resourceName, model).name("Dynamic process deployment").deploy();
        if (deployment != null) {
            DeploymentEntityManager deploymentEntityManager =
                    Context
                    .getCommandContext()
                    .getDeploymentEntityManager();

            DeploymentEntity deploymentEntity = deploymentEntityManager.findDeploymentById(deployment.getId());

            List<Map<String, Object>> ruleContextList = ruleContextList();
            if(!CollectionUtils.isEmpty(ruleContextList)){
                for(Map<String,Object> ruleMap: ruleContextList){
                    ResourceEntity resourceEntity = new ResourceEntity();
                    resourceEntity.setId(SUID.getUUID());
                    resourceEntity.setName(String.valueOf(ruleMap.get("ruleName"))+".drl");
                    resourceEntity.setBytes(String.valueOf(ruleMap.get("drlContext")).getBytes());
                    resourceEntity.setDeploymentId(deployment.getId());

                    deploymentEntity.addResource(resourceEntity);
                }
            }

            RulesDeployer rulesDeployer = new RulesDeployer();
            rulesDeployer.deploy(deploymentEntity,null);

            //加载关联规则 == 可以用于检查规则是否编辑有误
            KnowledgeBase knowledgeBase = buildComplexFlowProcess(ruleContextList());
//***********************************************************************************************************************************************************************************
            //参考RulesDeployer 提取
//            DeploymentManager deploymentManager = Context
//                    .getProcessEngineConfiguration()
//                    .getDeploymentManager();

//            ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl)activitiRule.getProcessEngine().getProcessEngineConfiguration();
//            DeploymentManager deploymentManager = ((ProcessEngineConfigurationImpl)activitiRule.getProcessEngine().getProcessEngineConfiguration()).getDeploymentManager();



            //关联知识仓库及部署
            //缓存是单机的，不是分布式的，需要自行实现
            //或者与流程关联，保存到数据库
//            https://blog.csdn.net/silent_zqy/article/details/70148298
//            deploymentManager.getKnowledgeBaseCache().add(deployment.getId(), knowledgeBase);
//            processEngineConfiguration.setDeploymentManager(deploymentManager);
            //-----------------------------------------------------------------------------
//***********************************************************************************************************************************************************************************
            //获取流程定义
            String deplyId = deployment.getId();
            ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().deploymentId(deplyId).singleResult();
            logger.info("processDefinition :{}",processDefinition);
            String processDefinitionId = processDefinition.getId();
            Map<String, Object> variables = new HashMap<>();
            variables.put("applyUser", "empoyee");
            variables.put("days", "3");

            if (processDefinition.isSuspended()) {
                activitiRule.getRepositoryService().activateProcessDefinitionById(processDefinitionId);
            }

            //全局业务key（如：以一次请求requestId），保证业务key与processInstance相关联
//            https://blog.csdn.net/songzheng_741/article/details/17199339
//            String businessKey = "globalBusinessKey";
            //获取流程实例
            logger.info("流程开始执行....");
            ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceById(processDefinitionId, businessKey, variables);

            boolean isSupend = processInstance.isSuspended();
            boolean isEnded = processInstance.isEnded();

//            logger.info("isSupend:{},isEnded:{}", isSupend,isEnded);

            // 5. Check if task is available
            List<Task> tasks = activitiRule.getTaskService().createTaskQuery()
                    .processInstanceId(processInstance.getId()).list();

//            logger.info("task size:{}", tasks.size());
            System.out.println("流程终了.......");

            //-------------------
        }
    }



    public static List<Map<String, Object>> ruleContextList(){
        List<Map<String, Object>> ruleContextList = new ArrayList<>();
        Map<String,Object> rule1Map = new HashMap<>();
        Map<String,Object> rule2Map = new HashMap<>();

        String rule1 =  "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_1\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 60\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
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

        rule1Map.put("drlContext",rule1);//rule1.getBytes(Charset.forName("UTF-8"))
        rule1Map.put("ruleName","bpm-flowgroup_1");

        String rule2 = "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_2\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 59\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
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
        //rule2.getBytes(Charset.forName("UTF-8"))
        rule2Map.put("ruleName","bpm-flowgroup_2");

        ruleContextList.add(rule1Map);
        ruleContextList.add(rule2Map);
        return ruleContextList;
    }

    public void createResource(String name, byte[] bytes, DeploymentEntity deploymentEntity) {
        ResourceEntity resource = new ResourceEntity();
        resource.setName(name);
        resource.setBytes(bytes);
        resource.setDeploymentId(deploymentEntity.getId());

        Context
                .getCommandContext()
                .getDbSqlSession()
                .insert(resource);
    }

    public DeploymentEntity getDeployment(String deploymentId) {
        DeploymentEntity deployment = Context
                .getCommandContext()
                .getDeploymentEntityManager()
                .findDeploymentById(deploymentId);
        return deployment;
    }

    public ResourceEntityManager getResourceEntityManager(){
        return  Context
                .getCommandContext()
                .getResourceEntityManager();
    }



    public KnowledgeBase buildComplexFlowProcess(List<Map<String, Object>> ruleContextList) {
        try{
//            logger.info("===加载流程规则。ruleContextList={}", JSON.toJSONString(ruleContextList));
            //2.生成规则KnowledgeBuilder、KnowledgeBase
            //加载规则，构建知识库
            KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //构建知识库配置信息
            org.drools.impl.adapters.KnowledgeBaseConfigurationAdapter config = (KnowledgeBaseConfigurationAdapter)KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
            config.setOption(MaxThreadsOption.get(32));//设置执行线程数目

            KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(config);

            //3. 根据已启用的版本号id查询规则内容
//            List<Map<String, String>> ruleContextList = buildFlowProcessDao.getRuleContextListByVersionId(versionId);
            //获取规则内容


            CompositeKnowledgeBuilder ckb = knowledgeBuilder.batch().type(ResourceType.DRL);
            if (!CollectionUtils.isEmpty(ruleContextList)) {
                for (Map<String, Object> ruleContextMap : ruleContextList) {
                    String drlContext = String.valueOf(ruleContextMap.get("drlContext"));
                    System.out.println(drlContext);
                    ckb.add(ResourceFactory.newByteArrayResource(drlContext.getBytes("utf-8")));
                }
            }
            //批量构建、编译
            ckb.build();
            //检查规则编译是否有误
            boolean hasErrors = knowledgeBuilder.hasErrors();
//            logger.info("*****,检查规则文件编译是否有错:hasErrors={}", hasErrors);
            if (hasErrors) {
                StringBuilder errorMsgBuilder = new StringBuilder();
                errorMsgBuilder.append(knowledgeBuilder.getResults());
                logger.info("*****Invalid knowledge base!!系统规则加载错误 ，原因 reason={}", knowledgeBuilder.getResults());
                //如果有错，批量移除
                KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
                for (KnowledgeBuilderError error : errors) {
                    logger.error("***************error.message={}", error.getMessage());
                    errorMsgBuilder.append(error.getMessage());
                }
                //先打印错误信息，再进行移除；否则，先移除的话，错误信息也会一并消失
                knowledgeBuilder.undo();
                //系统生成规则，加载规则引擎有误，拒绝开启版本状态
                logger.error("。失败原因：" + errorMsgBuilder.toString());
            }

            knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
//            KnowledgeBaseImpl intKbase = (KnowledgeBaseImpl) knowledgeBase;
            //[权宜之计]方便构建流程中使用，动态加载规则
//            KnowledgeStore.addKnowledgeBase(versionId, intKbase);

            //有流程，需引入JBPM
            //构建规则流程
//            RuleFlowProcess ruleFlowProcess = this.processDefinition(versionId);
//            RuleFlowProcess ruleFlowProcess = null;
//            if (ruleFlowProcess != null) {
//                logger.debug("将规则流程[ruleFlowProcess]添加到[intKbase]");
                //model 1: split不起效果[权宜之计：将split条件以规则形式加载到知识仓库中]
//                intKbase.addProcess(ruleFlowProcess);
                //model 2:ActionNode 判断逻辑需要写在consequence 中，否则NullPointerException
//                XmlBPMNProcessDumper dumper = XmlBPMNProcessDumper.INSTANCE;
//                String xml = dumper.dump(ruleFlowProcess);
//                knowledgeBuilder.add(ResourceFactory.newByteArrayResource(xml.getBytes()), ResourceType.BPMN2);
//            }

            //系统生成规则，加载规则引擎成功，开启版本状态
            logger.info("knowledgeBase....... success");

            return knowledgeBase;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("规则系统生成有误，加载失败，版本发布失败。",e);
        }
        logger.info("knowledgeBase....... failed");
        return null;
    }


    /**
     * 测试加载相同规则名称到知识仓库中
     */
    @Test
    public void duplicateRuleTest(){
        //加载关联规则 == 可以用于检查规则是否编辑有误
        for(int i = 0; i< 100000; i++){
            final int j = i;
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try{
                                KnowledgeBase knowledgeBase = buildComplexFlowProcess(duplicateRuleContextList());
                            }catch (Exception e){
                                System.out.println("第 【"+j+"】加载异常");
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
        }
    }




    public static List<Map<String, Object>> duplicateRuleContextList(){
        List<Map<String, Object>> ruleContextList = new ArrayList<>();
        Map<String,Object> rule1Map = new HashMap<>();
        Map<String,Object> rule2Map = new HashMap<>();

        String rule1 =  "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_1\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 60\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
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

        rule1Map.put("drlContext",rule1);//rule1.getBytes(Charset.forName("UTF-8"))
        rule1Map.put("ruleName","bpm-flowgroup_1");

        String rule2 = "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_2\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 59\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
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
        //rule2.getBytes(Charset.forName("UTF-8"))
        rule2Map.put("ruleName","bpm-flowgroup_2");

        ruleContextList.add(rule1Map);
        ruleContextList.add(rule2Map);
        return ruleContextList;
    }

}
