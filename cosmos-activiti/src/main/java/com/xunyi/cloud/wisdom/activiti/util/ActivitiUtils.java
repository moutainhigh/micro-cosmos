package com.xunyi.cloud.wisdom.activiti.util;

import com.xunyi.cloud.wisdom.activiti.enums.NodeTypeEnum;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.listeners.TaskLisnter;
import com.yichen.cosmos.cloud.platform.util.SUID;
import org.activiti.bpmn.model.*;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:thomas
 * @Date: 2018/7/5 13:53
 * @Description: fluent 构建实现
 */
public class ActivitiUtils {


    public static ScriptTask createScriptTask(String id,String name){
        ScriptTask scriptTask = new ScriptTask();
        scriptTask.setId(id);
        scriptTask.setName(name);
        scriptTask.setScriptFormat("groovy");
        scriptTask.setScript("println id:"+ id + ", name:"+ name);

        return scriptTask;
    }
    public static ServiceTask createServiceTask(String id,String name){
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId(id);
        serviceTask.setName(name);

        return serviceTask;
    }



    //监听器，动态添加
    public static  UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);

        //监听器的动态注入
        List<String> taskListenerList = new ArrayList<>();
//        taskListenerList.add("com.xunyi.cloud.wisdom.activiti.service.activitidrools.listeners.TaskLisnter");
        taskListenerList.add(TaskLisnter.class.getName());

        List<ActivitiListener> list = new ArrayList<>();
        for (String taskListener : taskListenerList) {
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent(TaskListener.EVENTNAME_ALL_EVENTS);
            //1. Spring配置以变量形式调用写入
            //2. 通过继承TaskListener方法（其它方法可参考 ImplementationType）
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
            listener.setImplementation(taskListener);
            list.add(listener);
        }

        userTask.setTaskListeners(list);


        return userTask;
    }

    //目前 BusinessRuleTask的规则执行后，监听器继续执行；可以尝试在监听器中将drools执行的决策结果放入到流程变量中
//    ${count>=1000}
    public static SequenceFlow createSequenceFlow(String from, String to,String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setId("条件ID_"+ SUID.getUUID());
        flow.setId("条件名称_"+ from + "_" + to);
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        if(StringUtils.isNotEmpty(conditionExpression)){
            System.out.println("规则条件.sourceRef:"+from+", targetRef:"+ to +",  conditionExpression:"+conditionExpression);
            flow.setConditionExpression(conditionExpression);
        }

        //2.监听器的动态注入
        List<String> taskListenerList = new ArrayList<>();
        taskListenerList.add(TaskLisnter.class.getName());

        List<ActivitiListener> list = new ArrayList<>();
        for (String taskListener : taskListenerList) {
            ActivitiListener listener = new ActivitiListener();

            //规则节点绑定的是 ExecutionListener ，所以事件的绑定需要注意，不能为 TaskListener的
            listener.setEvent(ExecutionListener.EVENTNAME_TAKE);
            //1. Spring配置以变量形式调用写入
            //2. 通过继承TaskListener方法（其它方法可参考 ImplementationType）
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
            listener.setImplementation(taskListener);
            list.add(listener);
        }

        flow.setExecutionListeners(list);

        return flow;
    }

    public static StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(NodeTypeEnum.START.name());
        startEvent.setName(NodeTypeEnum.START.getDesc());
        return startEvent;
    }

    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(NodeTypeEnum.END.name());
        endEvent.setName(NodeTypeEnum.END.getDesc());

        return endEvent;
    }

    public  static BusinessRuleTask businessRuleTask(String busId,String name,List<String> ruleNames){
        BusinessRuleTask businessRuleTask = new BusinessRuleTask();
        businessRuleTask.setId(busId);
        businessRuleTask.setName(name);
        if(CollectionUtils.isNotEmpty(ruleNames)){
            businessRuleTask.setRuleNames(ruleNames);
        }
        List<String> inputVariables = new ArrayList<>();
        //test.drl 如果 rule1 需要执行，必须插入Map实例
        //因为 rule1 的when条件使用的map
        //BusinessRuleTaskActivityBehavior
//        inputVariables.add("${yysContactLoanOrg}");
//        inputVariables.add("yysContactLoanOrg");
        inputVariables.add("${map}");//org.activiti.engine.impl.juel.AstEval
//        inputVariables.add("map");
        businessRuleTask.setInputVariables(inputVariables);

//        输出对象存储到一个流程变量中。注意，结果变量将包含一个对象列表。如果没有指定结果的变量名，默认使用
//        org.activiti.engine.rules.OUPUT。
//        rulesOutput
        businessRuleTask.setResultVariableName("rulesOutput");
        //=========================================================================================================
        //2.监听器的动态注入
        List<String> taskListenerList = new ArrayList<>();
        taskListenerList.add(TaskLisnter.class.getName());

        List<ActivitiListener> list = new ArrayList<>();
        for (String taskListener : taskListenerList) {
            ActivitiListener listener = new ActivitiListener();

            //规则节点绑定的是 ExecutionListener ，所以事件的绑定需要注意，不能为 TaskListener的
            listener.setEvent(ExecutionListener.EVENTNAME_END);
            //1. Spring配置以变量形式调用写入
            //2. 通过继承TaskListener方法（其它方法可参考 ImplementationType）
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
            listener.setImplementation(taskListener);
            list.add(listener);
        }

        businessRuleTask.setExecutionListeners(list);
        return businessRuleTask;
    }


    public static ExclusiveGateway createExclusiveGateway(String id,String name){
        ExclusiveGateway  exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
//        exclusiveGateway.setDefaultFlow();

        return exclusiveGateway;
    }



}
