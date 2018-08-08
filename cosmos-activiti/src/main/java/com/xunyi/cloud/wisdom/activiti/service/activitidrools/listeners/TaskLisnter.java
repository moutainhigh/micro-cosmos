package com.xunyi.cloud.wisdom.activiti.service.activitidrools.listeners;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.SpringContextHelper;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.TestCommonService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/7/6 14:45
 * @Description:
 */
public class TaskLisnter implements ExecutionListener,TaskListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskLisnter.class);
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        logger.info("测试动态绑定监听器，==== ActivitiEvent");
        logger.info("》》》》》》》》》》》》》》》》[节点]事件类型：{}",execution.getEventName());

        String id = execution.getId();
        logger.info("》》》》》》》》》》》》》》》》[节点]id：{}",id);
        //如果监听的是流程线事件
        if(execution.getEventName().equals(ExecutionListener.EVENTNAME_TAKE)){
            System.out.println("[监听到了流程线事件*****]，名称："+execution.getCurrentActivityName());
            Map<String, Object> variables = execution.getVariables();
            System.out.println("变量结果：variables："+ JSON.toJSONString(variables));

        }else if(execution.getEventName().equals(ExecutionListener.EVENTNAME_START)){
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>[节点]id：{}",id);
        }

        //20180730 测试 结束节点的 结束事件，执行结束后，获取流程中的所有变量
        if(execution.getEventName().equals(ExecutionListener.EVENTNAME_END)){
            logger.info("[测试节点结束事件]........");
            String currentActivityId = execution.getCurrentActivityId();
            logger.info("ID:{}",id);
            logger.info("currentActivityId:{}",currentActivityId);
        }
    }


    @Override
    public void notify(DelegateTask delegateTask) {
        String taskdbId = delegateTask.getId();
        String processInstanceId = delegateTask.getProcessInstanceId();
        Map<String, Object> variables = delegateTask.getVariables();
        logger.info("[监听器]变量 variables：{}",JSON.toJSONString(variables));

        String taskName = delegateTask.getName();

        String taskDefinitionKey = delegateTask.getTaskDefinitionKey();

        if(TaskListener.EVENTNAME_CREATE.equals(delegateTask.getEventName())){
            logger.info("[监听器]测试动态绑定监听器 [节点创建]== DelegateTask");
            logger.info("[监听器][节点]名称:{},taskdbId:{}",taskName,taskdbId);
            logger.info("[监听器][节点]:taskDefinitionKey:{}",taskDefinitionKey);

      /*      TaskService taskService  = (TaskService)SpringContextHelper.getBean("taskService");
            Task task = taskService.createTaskQuery().taskId(taskdbId).singleResult();
            String taskDefinitionKey = task.getTaskDefinitionKey();
            logger.info("[监听器][节点][ID]:{}",taskDefinitionKey);*/

            if(taskName.contains("UT")){
                logger.error("UserTask!!!!");
            }


            //流程启动时，设置初始变量值
         /*   Map initMap = VariablesUtil.variables.get();
            if(initMap != null && !initMap.isEmpty()){
                try{
                    initMap.put("processInstanceId",processInstanceId);
                    RuntimeService runtimeService = (RuntimeService)SpringContextHelper.getBean("runtimeService");
                    runtimeService.setVariable(processInstanceId, ActivitiConstants.FLOW_INPUT_VARIABLE_NAME,initMap);
                } finally {
                    VariablesUtil.variables.remove();
                }
            }*/

            //可以异步发起操作=============

            //可以调用其它服务
            TestCommonService testCommonService = (TestCommonService)SpringContextHelper.getBean("testCommonService");
            testCommonService.info();
        }
        if(TaskListener.EVENTNAME_COMPLETE.equals(delegateTask.getEventName())){
            logger.info("[监听器]测试动态绑定监听器》》》》》》 [节点完成]== DelegateTask");
            logger.info("[监听器][节点]名称:{}",taskName);

            logger.info("[监听器]delegateTask.getProcessInstanceId():{}",delegateTask.getProcessInstanceId());
            logger.info("[监听器]delegateTask.getExecutionId():{}",delegateTask.getExecutionId());

        }

        reloadVariables(processInstanceId,"TaskListener - DelegateTask");
    }

    /**
     * 目的：是将BusinessRuleTask决策结果存入到流程变量中（目前测试不可行）
     * @param processInstanceId
     * @param source
     */
    private void reloadVariables(String processInstanceId,String source){
        System.out.println("###########################################source:"+source);
        RuntimeService runtimeService =  (RuntimeService) SpringContextHelper.getBean("runtimeService");
        HistoryService historyService =  (HistoryService) SpringContextHelper.getBean("historyService");

        logger.info("runtimeService={}",runtimeService);
        logger.info("historyService={}",historyService);

        Map<String, Object> tmpVariables = runtimeService.getVariables(processInstanceId);
        // tmpVariables：{"rulesOutput":[{"result":"pass","name":"thomas"}],"map":{"$ref":"$.rulesOutput[0]"}}
        logger.info("[监听器]变量runtimeService》》   tmpVariables：{}",JSON.toJSONString(tmpVariables));
        if(tmpVariables != null && tmpVariables.size() != 0){
            Object outArr = tmpVariables.get("rulesOutput");
            logger.info("outArr:{}",JSON.toJSONString(outArr));
            if(outArr != null){
                if(outArr instanceof List){
                    System.out.println("***********outArr 是 list类型************************");
                }

                List<Map> list = (List<Map>)tmpVariables.get("rulesOutput");
                System.out.println("*********************list："+JSON.toJSONString(list));
                if(CollectionUtils.isNotEmpty(list)){

//                    runtimeService.setVariables(processInstanceId,list.get(0));

                    for(Map<String,Object> map: list){
                        for(Map.Entry<String,Object> entry: map.entrySet()){
                            System.out.println("###### entry.getKey()：" + entry.getKey() +",   entry.getValue():"+entry.getValue());
                            runtimeService.setVariable(processInstanceId,entry.getKey(),entry.getValue());
                        }
                    }
                }
            }

        }

        HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId);
        logger.info("[监听器]historicVariableInstanceQuery:{}",historicVariableInstanceQuery);
        if(historicVariableInstanceQuery != null){
            List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
            logger.info("执行过程中，流程未结束；所以变量还未移到历史表中。列表可能为空：variableInstances.size:{}",variableInstances.size());
            if(CollectionUtils.isNotEmpty(variableInstances)){
                for(HistoricVariableInstance hvi:variableInstances){
                    //注意：
                    //以BusinessRuleTask的入参和结果参数名为例：流程中存在多个规则节点，历史变量名如果相同的话，每次执行时会被覆盖；
                    //查询结果示例：
                    //查询流程历史变量值： 5b4aaa15e08442b4ab5958505b65677d  -  map	 -  {}
//                查询流程历史变量值： 8c00d6544e634f0d808cf16ccb167dc0  -  rulesOutput	 -  [{result=pass}]
                    System.out.println("[监听器]查询流程历史变量值： "+ hvi.getId()+"  -  "+hvi.getVariableName()+"	 -  "+hvi.getValue());

                    //delegateTask.getExecutionId(),hvi.getVariableName(),hvi.getValue()
                    runtimeService.setVariable(processInstanceId,hvi.getVariableName(),hvi.getValue());
                }
            }
        }
    }
}
