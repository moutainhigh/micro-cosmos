package com.xunyi.cloud.wisdom.activiti.service.chapter4.impl;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import com.xunyi.cloud.wisdom.activiti.enums.DiagramEnums;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service;

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

}
