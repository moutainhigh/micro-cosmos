package com.tairan.activiti.demo.chapter6_test;

import org.activiti.engine.*;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月31日 下午2:21:21 
 * @Description
 */
public class LeaveDynamicFormTest{

	@Autowired
	public RepositoryService repositoryService;
	@Autowired
	public RuntimeService runtimeService;
	@Autowired
	public TaskService taskService;
	@Autowired
	public IdentityService identityService;
	@Autowired
	public HistoryService historyService;
	@Autowired
	public FormService formService;
	@Autowired
	public ManagementService managementService;
	
	
	@Test
	@Deployment(resources="/chapter6/dynamic-form/leave.bpmn")
	//利用注解部署文件、本类未经测试
	public void allApproved() throws Exception{
		String currentUserId = "thomas";
		
		identityService.setAuthenticatedUserId(currentUserId);
		//启动流
		
		
		
	}
}
