package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.zero.BuilderComplexFlowProcessService;
import junit.framework.Assert;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:thomas
 * @Date: 2018/7/5 10:37
 * @Description:
 */
@Service
public class DynamicFlow_180705 extends BaseService implements IDynamicActivtiFlowDemo{

    @Autowired
    private BuilderComplexFlowProcessService builderComplexFlowProcessService;

    @Override
    public void createActivitiFlow() {
        String processName = "my_process";
        logger.warn("流程测试.................");

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(processName);

        process.addFlowElement(createStartEvent());
        process.addFlowElement(createUserTask("task1", "First task", "fred"));
        process.addFlowElement(createUserTask("task2", "Second task", "john"));
        process.addFlowElement(createEndEvent());

        process.addFlowElement(createSequenceFlow("start", "task1"));
        process.addFlowElement(createSequenceFlow("task1", "task2"));
        process.addFlowElement(createSequenceFlow("task2", "end"));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        String deployname = "Dynamic_process_deployment";
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentName(deployname).singleResult();
        if(deployment == null){
            // 3. Deploy the process to the engine
            deployment = repositoryService.createDeployment()
                    .addBpmnModel("dynamic-model.bpmn", model).name(deployname).deploy();
        }else{
            logger.warn("当前流程部署文件已经存在.deployname:{}",deployname);
        }

        // 4. Start a process instance
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processName);
        logger.warn("process isSuspended status:{},isended:{}",processInstance.isSuspended(),processInstance.isEnded());

        // 5. Check if task is available
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId()).list();

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("First task", tasks.get(0).getName());
        Assert.assertEquals("fred", tasks.get(0).getAssignee());
        logger.warn("processInstancesId:{},taskname:{},task assignee:{}",processInstance.getId(),tasks.get(0).getName(),tasks.get(0).getAssignee());

        taskService.complete(tasks.get(0).getId(),null);

        logger.warn("process isSuspended status:{},isended:{}",processInstance.isSuspended(),processInstance.isEnded());

        List<Task> task2s = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId()).list();

        Assert.assertEquals(1, task2s.size());

        logger.warn("processInstancesId:{},taskname:{},task assignee:{}",processInstance.getId(),task2s.get(0).getName(),task2s.get(0).getAssignee());

        logger.warn("process isSuspended status:{},isended:{}",processInstance.isSuspended(),processInstance.isEnded());
        taskService.complete(task2s.get(0).getId(),null);

        logger.warn("process isSuspended status:{},isended:{}",processInstance.isSuspended(),processInstance.isEnded());

        ProcessInstance prf = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        if(prf == null){
            logger.info("流程已结束....");
        }else{
            logger.info("流程未结束....");
        }

        logger.warn("process isSuspended status:{},isended:{}",processInstance.isSuspended(),processInstance.isEnded());

    }


    protected UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }

    protected SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

    protected StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        return startEvent;
    }

    protected EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        return endEvent;
    }


}
