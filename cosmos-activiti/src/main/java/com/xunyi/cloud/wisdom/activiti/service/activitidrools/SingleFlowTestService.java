package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunyi.cloud.wisdom.activiti.enums.NodeTypeEnum;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.util.ActivitiUtils;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.drools.core.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:thomas su
 * @Date: 2018/8/15 14:00
 * @Description:
 */
@Service
public class SingleFlowTestService extends BaseService {

    private static final String deploy_name_prefix = "deploy_name_";
    private static final String proc_def_name_prefix = "proc_def_name_";
    private static final String proc_def_id_prefix = "proc_def_id_";
    private static final String bpmn_model_name_prefix = "bpmn_model_name_";


    public void createDeployment(String strategyname){
        //1. 对旧的部署文件已经删除
        //分布式锁

        List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentName(deploy_name_prefix+strategyname).list();
        if(CollectionUtils.isNotEmpty(deployments)){
            logger.info("deployname={},部署文件已经存在，需删除，重新生成。deployments-size:{}",deploy_name_prefix+strategyname,deployments.size());
            try{
                for(Deployment deployment: deployments){
                    repositoryService.deleteDeployment(deployment.getId(),true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("流程文件不存在。名称：{}",deploy_name_prefix+strategyname);
        }

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(proc_def_id_prefix+strategyname);
        process.setName(proc_def_name_prefix+strategyname);

        process.addFlowElement(ActivitiUtils.createStartEvent());
        process.addFlowElement(ActivitiUtils.createUserTask("ut1","ut1","ts"));
        List<String> ruleNames = new ArrayList<>();
        ruleNames.add("【single_flow_rule_1】");
        process.addFlowElement(ActivitiUtils.businessRuleTask("bs1Id","bs1Name",ruleNames));
        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow(NodeTypeEnum.START.name(), "ut1",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("ut1", "bs1Id",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("bs1Id", NodeTypeEnum.END.name(),null));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        List<Map<String, Object>> ruleContextList = ruleContextList();
        if (!CollectionUtils.isEmpty(ruleContextList)) {
            for (Map<String, Object> ruleMap : ruleContextList) {
                logger.info("加载规则：{}",String.valueOf(ruleMap.get("ruleName")) + ".drl");
                logger.info("加载规则内容：{}",String.valueOf(ruleMap.get("drlContext")));
                deploymentBuilder
                        .addString(String.valueOf(ruleMap.get("ruleName")) + ".drl",String.valueOf(ruleMap.get("drlContext")));
            }
        }

        //后缀名 .bpmn 由 BpmnDeployer 加载解析
        deploymentBuilder
                .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model)
                .name(deploy_name_prefix+strategyname)
                .deploy();

        logger.warn("流程的重新部署................[完成].");
    }



    public Map completeTask(JSONObject params){

        String taskId = params.getString("taskId");
        if(StringUtils.isEmpty(taskId)){
            logger.info("任务ID不存在");
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("code",500);
            returnMap.put("msg","任务ID不存在");

            return returnMap;
        }


        Map<String,String> dataMap = new HashMap<>();

        //检查任务是否已经存在
        //防止重复执行
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            logger.info("任务不存在.说明已经执行完毕了》taskId:{}",taskId);
            dataMap.put("msg","任务不存在.说明已经执行完毕了》taskId:"+ taskId);
            return dataMap;
        }

        //将数据源反馈的因子值 K-V ==》流程中
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariables(processInstanceId,params);

        taskService.complete(taskId);

        logger.info("执行了任务。taskId:{}",taskId);

        //获取下一个任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

        //查询历史数据
        if(org.apache.commons.collections.CollectionUtils.isEmpty(tasks)){
            logger.info("没有需要执行的任务了，流程已经完结。流程实例ID：{}",processInstanceId);

            HistoryService historyService = (HistoryService) SpringContextHelper.getBean("historyService");
            HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId);
            if(historicVariableInstanceQuery != null){
                List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();

                if(org.apache.commons.collections.CollectionUtils.isNotEmpty(variableInstances)){
                    for(HistoricVariableInstance hvi:variableInstances){
                        //注意：
                        //以BusinessRuleTask的入参和结果参数名为例：流程中存在多个规则节点，历史变量名如果相同的话，每次执行时会被覆盖；
                        //查询结果示例：
                        //查询流程历史变量值： 5b4aaa15e08442b4ab5958505b65677d  -  map	 -  {}
                        //查询流程历史变量值： 8c00d6544e634f0d808cf16ccb167dc0  -  rulesOutput	 -  [{result=pass}]

                        System.out.println("解析数据为："+ JSON.toJSONString(hvi.getVariableName()));
                    }
                }
            }

            dataMap.put("msg","没有需要执行的任务了，流程已经完结");
            return dataMap;
        }

        logger.info("返回下一个任务ID:{}",tasks.get(0).getId());
        dataMap.put("taskId",tasks.get(0).getId());
        return dataMap;
    }


    public static List<Map<String, Object>> ruleContextList(){
        List<Map<String, Object>> ruleContextList = new ArrayList<>();
        Map<String,Object> rule1Map = new HashMap<>();

        String rule1 =  "\npackage bpm;\n" +
                "import java.util.Map; \n" +
                "rule \"【single_flow_rule_1】\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 60\n" +
                "    when\n" +
                "        eval(true)" +
                "        map:Map()   \n" +
                "    then\n" +
                "        System.out.println(drools.getRule());\n" +
                "        System.out.println(\"------------1111-------------------\");\n" +
                "        System.out.println(\"-------------1111------------------\");\n" +
                "        map.put(\"address\",\"china\");\n" +
                "        \n" +
                "end";

        rule1Map.put("drlContext",rule1);//rule1.getBytes(Charset.forName("UTF-8"))
        rule1Map.put("ruleName","【single_flow_rule_1】");

        ruleContextList.add(rule1Map);
        return ruleContextList;
    }
}
