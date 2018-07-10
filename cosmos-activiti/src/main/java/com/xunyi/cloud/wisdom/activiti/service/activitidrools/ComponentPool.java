package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import org.activiti.bpmn.model.*;

import java.util.List;
import java.util.UUID;

/**
 * @Author:thomas
 * @Date: 2018/6/27 20:36
 * @Description:
 */
public class ComponentPool {


    /**
     * 需要完善
     * @return
     *
     * https://www.cnblogs.com/ANCAN-RAY/p/6687640.html
     */
    public static ServiceTask serviceTask(){
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId("serviceTask_"+UUID.randomUUID());
        serviceTask.setName("ServiceTask");
        serviceTask.setImplementation("activitiTest.PrintVariables");
        String implementationType = ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION;
        serviceTask.setImplementationType(implementationType );
        return serviceTask;
    }
    public  static BusinessRuleTask businessRuleTask(List<String> ruleNames){
        BusinessRuleTask businessRuleTask = new BusinessRuleTask();
        businessRuleTask.setId("businessRuleTask_"+ UUID.randomUUID());
        businessRuleTask.setRuleNames(ruleNames);
        return businessRuleTask;
    }

    public static  SequenceFlow sequenceFlow(String from, String to) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setId("sequenceFlow_"+ UUID.randomUUID());
        sequenceFlow.setSourceRef(from);
        sequenceFlow.setTargetRef(to);
        return sequenceFlow;
    }

    public static  StartEvent startEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent_"+UUID.randomUUID());
        return startEvent;
    }

    public static  EndEvent endEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent_"+UUID.randomUUID());
        return endEvent;
    }

}
