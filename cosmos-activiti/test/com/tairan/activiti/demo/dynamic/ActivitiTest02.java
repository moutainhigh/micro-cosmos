package com.tairan.activiti.demo.dynamic;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Iterator;
/**
 * @Author:thomas
 * @Date: 2018/7/6 17:54
 * @Description:
 * https://blog.csdn.net/u012954390/article/details/51251374
 *
 * https://blog.csdn.net/qq_30739519/article/details/51248516
 *
 * http://blog.csdn.net/bluejoe2000/article/details/41778737
 */

/**
 *
 */

/**
 * @ClassName: ActivitiTest02
 * @Description: TODO(activiti节点跳转 )
 * @author liang
 * @date 2016年4月26日 上午11:23:34
 *
 */
public class ActivitiTest02 implements Command<Void> {

    private ProcessEngine processEngine;

    private String executionId;

    private ActivityImpl currentActivity;

    private ActivityImpl targetActivity;

    public ActivitiTest02() {
        super();
    }

    public ActivitiTest02(String executionId, ActivityImpl currentActivity, ActivityImpl targetActivity) {
        this.executionId = executionId;
        this.currentActivity = currentActivity;
        this.targetActivity = targetActivity;
    }

    {
        processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activitiDB")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setDatabaseSchemaUpdate("true")
                .setJobExecutorActivate(false)
                .buildProcessEngine();
    }

    /* (non-Javadoc)
     * @see org.activiti.engine.impl.interceptor.Command#execute(org.activiti.engine.impl.interceptor.CommandContext)
     */
    public Void execute(CommandContext arg0) {
        //并发的情况下executionId是唯一的
        ExecutionEntity executionEntity = (ExecutionEntity) processEngine.getRuntimeService().createExecutionQuery().executionId(this.executionId).singleResult();
        executionEntity.setActivity(this.currentActivity);
        executionEntity.setEventSource(this.currentActivity);
		/*List<Task> taskList=processEngine.getTaskService().createTaskQuery().executionId(this.executionId).list();
		if(taskList != null && taskList.size() > 0){
			//删除当前的任务,要先清除掉关联
			TaskEntity taskEntity=(TaskEntity) taskList.get(0);
			taskEntity.setExecutionId(null);
			processEngine.getTaskService().saveTask(taskEntity);
			processEngine.getTaskService().deleteTask(taskEntity.getId(), false);
		}*/
        //根据executionId 获取Task
        Iterator<TaskEntity> localIterator = Context.getCommandContext().getTaskEntityManager().findTasksByExecutionId(this.executionId).iterator();
        while (localIterator.hasNext()) {
            TaskEntity taskEntity = (TaskEntity) localIterator.next();
            //删除任务
            Context.getCommandContext().getTaskEntityManager().deleteTask(taskEntity, "跳转节点", false);
        }
        executionEntity.executeActivity(this.targetActivity);
        return null;
    }

    public void test02(String processInstanceId, String targetTask) {
        System.out.println("...start...");
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processInstance.getProcessDefinitionId());
        TaskEntity taskEntity = (TaskEntity) processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        //当前正在执行任务的节点
        ActivityImpl cActivity = (ActivityImpl) processDefinitionEntity.findActivity(processInstance.getActivityId());
        //跳转的目标节点
        ActivityImpl tActivity = (ActivityImpl) processDefinitionEntity.findActivity(targetTask);
        processEngine.getManagementService().executeCommand(new ActivitiTest02(taskEntity.getExecutionId(), cActivity, tActivity));
        System.out.println("...end...");
    }

    public static void main(String[] args) {
        ActivitiTest02 activitiTest02 = new ActivitiTest02();
        activitiTest02.test02("5", "task3");
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public ActivityImpl getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(ActivityImpl currentActivity) {
        this.currentActivity = currentActivity;
    }

    public ActivityImpl getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(ActivityImpl targetActivity) {
        this.targetActivity = targetActivity;
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
}
