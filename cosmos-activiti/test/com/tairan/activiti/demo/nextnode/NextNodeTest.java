package com.tairan.activiti.demo.nextnode;

import org.activiti.engine.impl.task.TaskDefinition;
import org.junit.Test;

/**
 * @Author:thomas su
 * @Date: 2018/8/4 11:43
 * @Description:
 */
public class NextNodeTest {
    @Test
    public void nextTaskInfo() throws Exception{
        ActivitiNextTask task = new ActivitiNextTask();
        String processInstanceId = "387905";
        TaskDefinition nextTaskGroup = task.getNextTaskInfo(processInstanceId);
        System.out.println(nextTaskGroup.getNameExpression().getExpressionText());
    }
}
