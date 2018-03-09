package com.xunyi.cloud.wisdom.activiti.service;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
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
	
	public ProcessInstance startProcessInstanceById(String processInstanceId){
		ProcessInstance  processInstance = runtimeService.startProcessInstanceById(processInstanceId);
		return processInstance;
	}
}
