package com.yichen.cosmos.cloud.platform.service;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
	public static final Logger logger = LoggerFactory.getLogger(BaseService.class);
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
	@Autowired
	public ProcessEngineFactoryBean processEngine;

	@Autowired
	public SpringProcessEngineConfiguration processEngineConfiguration;
	
	public ProcessInstance startProcessInstanceById(String processInstanceId){
		ProcessInstance  processInstance = runtimeService.startProcessInstanceById(processInstanceId);
		return processInstance;
	}
}
