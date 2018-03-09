/**
 * 
 */
package com.xunyi.cloud.wisdom.activiti.service.impl;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.service.LeaveSayHelloService;

/**
 * http://blog.csdn.net/xxb2008/article/details/8480506
 * ProcessDefinition ：
          这个最好理解，就是流程的定义，也就相当于规范。ProcessDefinition也有id，这个id是 {key}-{version}，在部署过程中，会把一个id分配给流程定义。 这个id的格式为{key}-{version}， key和version之间使用连字符连接。
	如果没有提供key， 会在名字的基础自动生成。 生成的key会把所有不是字母和数字的字符替换成下划线。
	ProcessInstance ：
	这个也比较好理解，就是流程的一个实例。
	在系统中，也有一个ProcessInstance的Id
	key可以用来创建流程实例的id， 格式为{process-key}.{execution-id}。
	如果没有提供用户定义的key，数据库就会把主键作为key。
	用Java来讲： ProcessDefinition就是代码中的一个类，而ProcessInstance就是把这个类new了出来，创建了一个实例；
	 * 
 * @Description:
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午2:10:26 
 */
@Service("leaveSayHelloService")
public class LeaveSayHelloServiceImpl extends BaseService implements LeaveSayHelloService {

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.LeaveSayHelloService#startProcessOfLeaveSayHello()
	 */
	@Override
	public void startProcessOfLeaveSayHello() {
		String fileUrl = "diagram/leavesayhello.bpmn";
		Deployment deployment = repositoryService.createDeployment().addClasspathResource(fileUrl).deploy();
		if(deployment != null){
			logger.info("deployment id:{},name:{},time:{}"
					,deployment.getId()
					,deployment.getName()
					,deployment.getDeploymentTime());
			
			String deploymentId = deployment.getId();
			ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			if(processDefinition != null){
				String deployId = processDefinition.getDeploymentId();
				String diagramResourceName = processDefinition.getDiagramResourceName();
				String processDefinitionId = processDefinition.getId();
				String processDefinitionKey = processDefinition.getKey();
				String name = processDefinition.getName();
				String resourceName = processDefinition.getResourceName();
				logger.info("deployId={}",deployId);
				logger.info("diagramResourceName={}",diagramResourceName);
				logger.info("processDefinitionId={}",processDefinitionId);
				logger.info("processDefinitionKey={}",processDefinitionKey);
				logger.info("name={}",name);
				logger.info("resourceName={}",resourceName);
				
				if(processDefinition.isSuspended()){
					repositoryService.activateProcessDefinitionById(processDefinitionId);
				}
				
				//启动实例
				ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
				if(processInstance != null){
					logger.info("流程实例已启动...");
				}
			}
		}
		
		
	}
	
}
