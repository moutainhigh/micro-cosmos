package com.xunyi.cloud.wisdom.activiti.service.chapter4.impl;

import com.xunyi.cloud.wisdom.activiti.enums.DiagramEnums;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月10日 下午6:34:01 
 * @Description
 */
@Service("chapter4Service")
public class Chapter4ServiceImpl extends BaseService implements Chapter4Service {

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service#startProcessOfTimeCycle()
	 * 
	 */
	@Override
	public void startProcessOfTimeCycle() {
		
		
		//如果已经加载了，就无需重新加载;倒排序，查询最新版本的
		//问题：有可能因为之前加载的工作流文件是错误的，导致最最新的加载不了，可以手动在表(act_re_procdef)中，
		//将旧的流程定义文件记录删除
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DiagramEnums.timecycle.getProcessKey()).orderByProcessDefinitionVersion().desc().singleResult();
		
		if(processDefinition == null){
			
			Deployment deployment = repositoryService.createDeployment().addClasspathResource(DiagramEnums.timecycle.getPath()).deploy();
			if(deployment != null){
				String deploymentId = deployment.getId();
					 processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			}
		}
		 
		
		if(processDefinition != null){
			
			int version = processDefinition.getVersion();
			logger.info("version={}",version);
			
			
			 String processDefinitionId = processDefinition.getId();
			 if( processDefinition.isSuspended()){
				 repositoryService.activateProcessDefinitionById(processDefinitionId);
			 }
			 ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
			 String processInstanceId = processInstance.getId();
			 logger.info("processInstanceId={}",processInstanceId);
		 }
	}

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service#startProcessOfReceiveTask()
	 */
	@Override
	public void startProcessOfReceiveTask() {
		
		//流程部署，定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DiagramEnums.receiveTask.getProcessKey()).orderByProcessDefinitionVersion().desc().singleResult();
		if(processDefinition == null){
			
			Deployment deployment = repositoryService.createDeployment().addClasspathResource(DiagramEnums.receiveTask.getPath()).deploy();
			if(deployment != null){
				String deploymentId = deployment.getId();
					 processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			}
		}
		 
		//流程实例启动
		if(processDefinition != null){
			
			int version = processDefinition.getVersion();
			logger.info("version={}",version);
			
			
			 String processDefinitionId = processDefinition.getId();
			 if( processDefinition.isSuspended()){
				 repositoryService.activateProcessDefinitionById(processDefinitionId);
			 }
			 ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
			 
			 String processInstanceId = processInstance.getId();
			 logger.info("processInstanceId={}",processInstanceId);
			 
			 
			 //查询执行对象表,使用流程实例ID和当前活动的名称（receivetask1）  
			 
			 String activitiId1 = "dailySellTotal";
			 Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).activityId(activitiId1).singleResult();
			 String exeId1 = execution.getId();
//			 使用流程变量设置当日的销售额 
			 runtimeService.setVariable(exeId1, "dailyAmount", 20000);
			 
			 //向后执行一步
			//activiti-version>5.18.0
//			 runtimeService.signal(exeId1);
			 //查询执行对象表,使用流程实例ID和当前活动的名称（receivetask2） 
			 String acId2="sendEmailToBoss";
			 Execution execution2 = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).activityId(acId2).singleResult();
			 String exeId2 = execution2.getId();
			 
			 Integer dailyAmount = (Integer)runtimeService.getVariable(exeId2, "dailyAmount");
			 logger.info("给老板发送短信：内容，当日销售额：{}",dailyAmount);
//			 向后执行一步
			//activiti-version>5.18.0
//			 runtimeService.signal(exeId2);
			 
			 //判断流程是否结束  
			 ProcessInstance nowP1 = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			 if(nowP1 == null){
				 logger.info("流程已经结束了....");
			 }
			 
		 }
	}

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service#startProcessOfSplitTest()
	 */
	@Override
	public void startProcessOfSplitTest(Integer day) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DiagramEnums.splitflow.getProcessKey()).orderByProcessDefinitionVersion().desc().singleResult();
		
		if(processDefinition == null){
			
			Deployment deployment = repositoryService.createDeployment().addClasspathResource(DiagramEnums.splitflow.getPath()).deploy();
			if(deployment != null){
				String deploymentId = deployment.getId();
					 processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			}
		}
		 
		
		if(processDefinition != null){
			
			int version = processDefinition.getVersion();
			logger.info("version={}",version);
			
			
			 String processDefinitionId = processDefinition.getId();
			 if( processDefinition.isSuspended()){
				 repositoryService.activateProcessDefinitionById(processDefinitionId);
			 }
			 
			 Map<String,Object> variables = new HashedMap();
			 variables.put("day", day);
			 
			 ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,variables);
			 String processInstanceId = processInstance.getId();
			 logger.info("processInstanceId={}",processInstanceId);
		 }
		
	}

}
