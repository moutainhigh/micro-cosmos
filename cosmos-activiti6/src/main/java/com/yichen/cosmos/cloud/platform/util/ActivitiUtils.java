package com.yichen.cosmos.cloud.platform.util;

import com.yichen.cosmos.cloud.platform.enums.NodeTypeEnum;
import com.yichen.cosmos.cloud.platform.listener.CustomTransactionTaskListener;
import org.activiti.bpmn.model.*;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.TransactionDependentTaskListener;
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
        taskListenerList.add(CustomTransactionTaskListener.class.getName());

        List<ActivitiListener> list = new ArrayList<>();
        for (String taskListener : taskListenerList) {
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent(TaskListener.EVENTNAME_CREATE);
            //1. Spring配置以变量形式调用写入
            //2. 通过继承TaskListener方法（其它方法可参考 ImplementationType）
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
            listener.setImplementation(taskListener);
            listener.setOnTransaction(TransactionDependentTaskListener.ON_TRANSACTION_COMMITTED);
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
        return flow;
    }

    public static SequenceFlow createSequenceFlow(String id,String from, String to,String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setId("条件ID_"+ SUID.getUUID());
        flow.setId("条件名称_"+ from + "_" + to);
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setId(id);
        if(StringUtils.isNotEmpty(conditionExpression)){
            System.out.println("规则条件.sourceRef:"+from+", targetRef:"+ to +",  conditionExpression:"+conditionExpression);
            flow.setConditionExpression(conditionExpression);
        }


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

    public static ExclusiveGateway createExclusiveGateway(String id,String name){
        ExclusiveGateway  exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);

        return exclusiveGateway;
    }

    /**
     * 排他网关设置默认流
     * @param id
     * @param name
     * @param defaultFlow
     * @return
     */
    public static ExclusiveGateway createExclusiveGateway(String id,String name, String defaultFlow){
        ExclusiveGateway  exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
        exclusiveGateway.setDefaultFlow(defaultFlow);

        return exclusiveGateway;
    }



}
