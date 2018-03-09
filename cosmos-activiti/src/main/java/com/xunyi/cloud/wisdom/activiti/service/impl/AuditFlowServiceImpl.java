package com.xunyi.cloud.wisdom.activiti.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import com.xunyi.cloud.wisdom.activiti.service.AuditFlowService;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午5:09:20 
 * @Description
 */
@Service("auditFlowService")
public class AuditFlowServiceImpl extends BaseService implements AuditFlowService{

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.AuditFlowService#startProcessOfAuditFlow()
	 */
	@Override
	public void startProcessOfAuditFlow() {

		//基本流程：部署（deployment）-》流程定义文件（processDefinition）->检查状态，并激活
//		-> 获取流程实例 --->执行流程--->任务查询--->任务认领-->继续执行
		
		String fileUrl = "diagram/auditflow.bpmn";
		
		//这里每次调用接口，都重新进行了部署，如果文件没有改动，只部署一次即可，因为数据库已经保存流程文件信息
		//可以参照表：act_ge_bytearray
		Deployment deployment = repositoryService.createDeployment().addClasspathResource(fileUrl).deploy();
		if(deployment != null){
			String deplyId = deployment.getId();
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deplyId).singleResult();
			String processDefinitionId = processDefinition.getId();
			Map<String,Object> variables = new HashMap<>();
			variables.put("applyUser", "empoyee");
			variables.put("days","3");
			
			if(processDefinition.isSuspended()){
				repositoryService.activateProcessDefinitionById(processDefinitionId);
			}
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, variables);
			
			String taskCandidateGroup = "deptLeader";
			Task deptLeaderTask = taskService.createTaskQuery().taskCandidateGroup(taskCandidateGroup).singleResult();
			
			String taskId = deptLeaderTask.getId();
			taskService.claim(taskId, "leaderUser");//userId,用户认领task
			
			variables = new HashMap<>();
			variables.put("approved", true);
			
			taskService.complete(taskId, variables);
			
			long count = historyService.createHistoricProcessInstanceQuery().finished().count();
			logger.info("process completed. count={}",count);
			
		}
	}
		
}
