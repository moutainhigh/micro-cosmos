package com.xunyi.cloud.wisdom.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xunyi.cloud.wisdom.activiti.enums.DiagramEnums;

/**
 * 加载activiti-bpmn文件
 * 1. 第一次加载，将文件数据信息保存到数据库中
 * 2. 当系统再次重启，指定processInstanceId执行工作流时，当工作流不存在时(activiti缓存没有)，则从数据库里读取工作流文件，
 * 并加载到activiti缓存中
 * 
 * 
 * 
 * 
 * 获取xml信息
 * 获取image信息
 * 
 * 可参照 test包中的 Bpmn2XmlTest.java
 * @author thomas
 *
 */
@RestController
@RequestMapping(value = "/loanbpmn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoadbpmnFileController {
private static final Logger logger = LoggerFactory.getLogger(LoadbpmnFileController.class);
	
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
	 * 获取XML文件
	 * 请求路径： http://localhost:33002/xunyi-activiti/loanbpmn/getXmlResource
	 * @Title:        getXmlResource 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @param response
	 * @param:        @throws IOException    
	 * @return:       void    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月13日 下午5:29:03
	 */
	@RequestMapping(value = "/getXmlResource", method = RequestMethod.GET)
	public void getXmlResource(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DiagramEnums.testxmlimage.getProcessKey()).orderByProcessDefinitionVersion().desc().singleResult();
		
		if(processDefinition == null){
			
			Deployment deployment = repositoryService.createDeployment().addClasspathResource(DiagramEnums.testxmlimage.getPath()).deploy();
			if(deployment != null){
				String deploymentId = deployment.getId();
				processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			}
		}
		 
		if(processDefinition.isSuspended()){
			repositoryService.activateProcessDefinitionById(processDefinition.getId());
		}
		
		//获取流程资源的名称
		String sourceName = processDefinition.getResourceName();
		//获取流程资源
		InputStream inputStream = repositoryService.getResourceAsStream(
			processDefinition.getDeploymentId(),sourceName);
		
		byte[] bytes = IOUtils.toByteArray(inputStream);
		response.getOutputStream().write(bytes);
		response.flushBuffer();
	}
	
	

	/**
	 * 获取Image文件
	 * 请求路径： http://localhost:33002/xunyi-activiti/loanbpmn/getImageResource
	 * @Title:        getImageResource 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @param response
	 * @param:        @throws IOException    
	 * @return:       void    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月13日 下午5:30:24
	 */
	@RequestMapping(value = "/getImageResource", method = RequestMethod.GET)
	public void getImageResource(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DiagramEnums.testxmlimage.getProcessKey()).orderByProcessDefinitionVersion().desc().singleResult();
		
		if(processDefinition == null){
			
			Deployment deployment = repositoryService.createDeployment().addClasspathResource(DiagramEnums.testxmlimage.getPath()).deploy();
			if(deployment != null){
				String deploymentId = deployment.getId();
					 processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			}
		}
		 
		if(processDefinition.isSuspended()){
			repositoryService.activateProcessDefinitionById(processDefinition.getId());
		}
		//获取流程资源的名称
		String diagramResourceName = processDefinition.getDiagramResourceName();
		//获取流程资源
		InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),diagramResourceName);
		
		//1. 设置 "Content-Type","image/jped"，加上这句话，浏览器会弹窗，下载图片
		//2. 不加上这句话，会直接在浏览器显示图片
//		response.setHeader("Content-Type","image/jped");//设置响应的媒体类型，这样浏览器会识别出响应的是图片
		byte[] bytes = IOUtils.toByteArray(inputStream);
		response.getOutputStream().write(bytes);
		response.flushBuffer();
	}
	
	/**
	 * 资源文件上传
	 * @Title:        deployfile 
	 * @Description:  
	 * @param:        @param file
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月14日 下午3:08:12
	 */
	@RequestMapping(value = "/deployfile", method = RequestMethod.GET)
	public String deployfile(@RequestParam(value="file") MultipartFile file){
		
		//获取上传文件的文件名
		String filename = file.getOriginalFilename();
		try{
			//得到输入流（字节流）对象
			InputStream inputStream = file.getInputStream();
			//文件的扩展名
			String extension = FilenameUtils.getExtension(filename);
			//zip 或bar类型的文件使用ZipInputStream 部署
			DeploymentBuilder deployment = repositoryService.createDeployment();
//			if(Arrays.asList("zip","bar").contains(extension)){}
			
			if("zip".equalsIgnoreCase(extension)
					|| "bar".equalsIgnoreCase(extension)){
				ZipInputStream zipInputStream = new ZipInputStream(inputStream);
				deployment.addZipInputStream(zipInputStream);
			}else{
				//其他类型的文件直接部署
				deployment.addInputStream(filename, inputStream);
			}
			deployment.deploy();
			
		}catch(Exception e){
			logger.error("error on deploy process,because of file input stream");
		}
		
		return "文件上传完毕!";
	}
	
	
	
	
	
}
