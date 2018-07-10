package com.tairan.activiti.demo.dynamic;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:thomas
 * @Date: 2018/6/28 18:15
 * @Description:
 */
public class AutodeployProcessBpmnAndImageUtils {

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();
    private final String START_EVENT = "start";
    private final String END_EVENT = "end";
    private final String PROCESSID = "PROCESSID"; //流程id
    private final String PROCESSNAME = "PROCESSNAME"; //流程名字



    /**
     *
     * @throws Exception
     */
    @Test
    public void dynamicDeployBpmnAndImage() throws Exception {


        System.out.println(".........start...");
//	String basePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+"bpmn\\";
//	System.out.println(".........start...====="+basePath);
// 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        org.activiti.bpmn.model.Process process=new org.activiti.bpmn.model.Process();
        model.addProcess(process);
        process.setId(PROCESSID);
        process.setName(PROCESSNAME);
//设置开始节点
        process.addFlowElement(createStartEvent());
//设置任务节点1
        process.addFlowElement(createUserTask("task1", "组长审核", "candidateGroup1"));
//设置排他节点1
        process.addFlowElement(createExclusiveGateway("createExclusiveGateway1"));
//设置任务节点2
        process.addFlowElement(createUserTask("task2", "项目经理审核", "candidateGroup2"));
//设置排他节点2
        process.addFlowElement(createExclusiveGateway("createExclusiveGateway2"));
//设置任务节点3 普通任务
//	process.addFlowElement(createUserTask("task3", "产品部门经理审核", "candidateGroup3"));
//设置任务节点3 监听任务
        List<String> lisenerList = new ArrayList<String>();
        lisenerList.add("com.tairan.activiti.demo.dynamic.MangerTaskHandlerCandidateUsers");
        process.addFlowElement(createUserTask("task3", "产品部门经理审核",lisenerList));
//设置排他节点3
        process.addFlowElement(createExclusiveGateway("createExclusiveGateway3"));
        process.addFlowElement(createUserTask("task4", "总经理审核", "candidateGroup4"));
//设置结束节点
        process.addFlowElement(createEndEvent());

        process.addFlowElement(createSequenceFlow(START_EVENT, "task1", "", ""));
        process.addFlowElement(createSequenceFlow("task1", "task2", "", ""));
//
        process.addFlowElement(createSequenceFlow("task2", "createExclusiveGateway1"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway1", "task1", "不通过", "${pass=='2'}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway1", "task3", "通过", "${pass=='1'}"));
        process.addFlowElement(createSequenceFlow("task3", "createExclusiveGateway2"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway2", "task2", "不通过", "${pass=='2'}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway2", "task4", "通过", "${pass=='1'}"));
        process.addFlowElement(createSequenceFlow("task4", "createExclusiveGateway3"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway3", "task3", "不通过", "${pass=='2'}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway3", END_EVENT, "通过", "${pass=='1'}"));
// 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

// 3. Deploy the process to the engine
        Deployment deployment = activitiRule.getRepositoryService().createDeployment().addBpmnModel(PROCESSID+".bpmn", model).name(PROCESSID+"_deployment").deploy();

// 4. Start a process instance
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(PROCESSID);

// 5. Check if task is available
//        List<org.activiti.bpmn.model.Task> tasks = activitiRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).list();
//	Assert.assertEquals(1, tasks.size());

// 6. Save process diagram to a file
        InputStream processDiagram = activitiRule.getRepositoryService().getProcessDiagram(processInstance.getProcessDefinitionId());
        FileUtils.copyInputStreamToFile(processDiagram, new File("target/"+PROCESSID+".png"));

// 7. Save resulting BPMN xml to a file
        InputStream processBpmn = activitiRule.getRepositoryService().getResourceAsStream(deployment.getId(), PROCESSID+".bpmn");
        FileUtils.copyInputStreamToFile(processBpmn,new File("target/"+PROCESSID+".bpmn"));

        System.out.println(".........end...");
    }
    /**
     * 创建节点任务 个人任务
     * @param id 任务id标识
     * @param name 任务名称
     * @param assignee 指定个人任务
     * @return
     */
    public UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }
    /**
     * 创建节点任务 多人任务
     * @param id 任务id标识
     * @param name 任务名称
     * @param candidateUsers 任务人的集合
     * @return
     */
    public UserTask createUserTask(String id, String name, String[] candidateUsers) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        if(null!=candidateUsers&&candidateUsers.length>0){
            userTask.setCandidateUsers(Arrays.asList(candidateUsers));
        }
        return userTask;
    }
    /**
     * 创建节点任务 使用监听设置处理人
     * @param id 任务id标识
     * @param name 任务名称
     * @param taskListenerList 监听的集合,TaskListener实现类的的具体路径例：com.sky.bluesky.activiti.utils.MangerTaskHandlerCandidateUsers
     * @return
     */
    public UserTask createUserTask(String id, String name, List<String> taskListenerList) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        List<ActivitiListener> list = new ArrayList<ActivitiListener>();
        for (String taskListener : taskListenerList) {
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent("create");
            //Spring配置以变量形式调用无法写入，只能通过继承TaskListener方法，
            listener.setImplementationType("class");
            listener.setImplementation(taskListener);
            list.add(listener);
        }
        userTask.setTaskListeners(list);
        return userTask;
    }


    /**
     * 设置连线
     * @param from 从哪里出发
     * @param to 连接到哪里
     * @return
     */
    public SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

    /**
     * 设置起始节点
     * @return
     */
    public StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(START_EVENT);
        return startEvent;
    }

    /**
     * 排他网关节点
     * @param id
     * @return
     */
    public static ExclusiveGateway createExclusiveGateway(String id) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        return exclusiveGateway;
    }
    /**
     * 设置结束节点
     * @return
     */
    public EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(END_EVENT);
        return endEvent;
    }
    /**
     * 设置连线
     * @param from 从哪里出发
     * @param to 连接到哪里
     * @param name 连线名称
     * @param conditionExpression 判断条件${arg>2}
     * @return
     */
    public static SequenceFlow createSequenceFlow(String from, String to, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        if(StringUtils.isNotEmpty(conditionExpression)){
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }
    public FlowElement createServiceTask(String name){
        ServiceTask stask = new ServiceTask();
        stask.setId("sid");
        stask.setName(name);
        stask.setImplementation("activitiTest.PrintVariables");
        String implementationType = ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION;
        stask.setImplementationType(implementationType );
        return stask;
    }

}
