package com.tairan.activiti.demo.dynamic.examples;

import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/7/9 10:43
 * @Description:
 */
public class Demo_001 extends BaseService{

    //Activiti获取当前活动（任务）的出口（动态生成提交按钮）
    //根据任务Id获取出口集合
    public List<String> getOutGoingTransNames(String taskId) {
        List<String> transNames = new ArrayList<>();
        // 1.获取流程定义
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        // 2.获取流程实例
        ProcessInstance pi =runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        // 3.通过流程实例查找当前活动的ID
        String activitiId = pi.getActivityId();
        // 4.通过活动的ID在流程定义中找到对应的活动对象
        ActivityImpl activity = pd.findActivity(activitiId);
        // 5.通过活动对象找当前活动的所有出口
        List<PvmTransition> transitions =  activity.getOutgoingTransitions();
        // 6.提取所有出口的名称，封装成集合
        for (PvmTransition trans : transitions) {
            String transName = (String) trans.getProperty("name");
            if(StringUtils.isNotBlank(transName)){
                transNames.add(transName);
            }
        }
        if(transNames.size()==0){
            transNames.add("提交");//默认
        }
        return transNames;
    }

    public void test_002(String taskId,String outcome){
        Map<String, Object> vars = new HashMap<>();
        vars.put("outcome", outcome);//outcome为提交按钮的名称
        // 完成任务
        taskService.complete(taskId,vars);
    }
}
