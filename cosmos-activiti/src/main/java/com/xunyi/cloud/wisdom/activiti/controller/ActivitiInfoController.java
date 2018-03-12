package com.xunyi.cloud.wisdom.activiti.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;

/**3924325588204
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午11:11:52 
 * @Description
 * 获取已经发布的流程信息
 */
@RestController
@RequestMapping(value = "/activitiInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivitiInfoController {
	private static final Logger logger = LoggerFactory.getLogger(ActivitiInfoController.class);
	
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
	
	
	/**
	 * 请求路径 http://localhost:33002/xunyi-activiti/activitiInfo/deleteCascadeOneKey
	 * http://blog.csdn.net/lifupingcn/article/details/61919018
	 * 级联删除 已经在使用的流程实例信息也会被级联删除
	 * @Title:        deleteCascade 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月12日 下午3:28:31
	 * 
	 * 一键清空所有部署
	 */
	@RequestMapping(value = "/deleteCascadeOneKey", method = RequestMethod.GET)
	public String deleteCascade(HttpServletRequest request){
		
		
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		logger.info("deployments .size()= {}",deployments.size());
		
		List<String> deployIds = deployments.stream().map(Deployment::getId).collect(Collectors.toList());
		
		if(CollectionUtils.isNotEmpty(deployIds)){
			deployIds.stream().forEach(
					deployId ->
					repositoryService.deleteDeployment(deployId, true));
		}
		
		
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getContextPath())
               .build().toString();
	}
	
	
	/**
	 * 请求路径 http://localhost:33002/xunyi-activiti/activitiInfo/getAllListInfo
	 * @Title:        getAllListInfo 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月12日 上午9:15:19
	 * 
	 * 问题1：
	 *  json 序列化会导致异常
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/getAllListInfo", method = RequestMethod.GET)
	public String getAllListInfo(HttpServletRequest request){
		
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		logger.info("deployments .size()= {}",deployments.size());
		
		Map<String,Date> deploys = deployments.stream().collect(Collectors.toMap(Deployment::getId, Deployment::getDeploymentTime));
			
		logger.info("deploys={}",JSON.toJSONString(deploys));
		//		List<ProcessDefinition> pds = repositoryService.createProcessDefinitionQuery().list();
//		logger.info("pds = {}",pds.size());
//		List<Model> models =   repositoryService.createModelQuery().list();
//		logger.info("models = {}",models.size());
//		List<Deployment> nativeDeployments = repositoryService.createNativeDeploymentQuery().list();
//		List<ProcessDefinition> nativepds = repositoryService.createNativeProcessDefinitionQuery().list();
//		List<Model> nativeModels = repositoryService.createNativeModelQuery().list();
//		
//		logger.info("nativeDeployments = {}",JSON.toJSONString(nativeDeployments));
//		logger.info("nativepds = {}",JSON.toJSONString(nativepds));
//		logger.info("nativeModels = {}",JSON.toJSONString(nativeModels));
		
		
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getContextPath())
                .build().toString();
	}
}
