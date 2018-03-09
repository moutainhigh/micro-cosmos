/**
 * 
 */
package com.xunyi.cloud.wisdom.activiti.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xunyi.cloud.wisdom.activiti.service.AuditFlowService;
import com.xunyi.cloud.wisdom.activiti.service.LeaveSayHelloService;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;

/**
 * @Description:
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午2:09:19 
 */
@RestController
@RequestMapping(value = "/action", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActionController {
	private static final Logger logger = LoggerFactory.getLogger(ActionController.class);

	
	@Autowired
	private LeaveSayHelloService leaveSayHelloService;
	
	@Autowired
	private AuditFlowService auditFlowService;
	
	/**
	 * 
	 * @Title:        startProcessOfAuditFlow 
	 * @Description:  
	 *  用户任务 UserTask
	 *  脚本任务 ScriptTask
	 *  
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月9日 下午5:39:44
	 */
	@RequestMapping(value = "/startProcessOfAuditFlow", method = RequestMethod.GET)
	public String startProcessOfAuditFlow(HttpServletRequest request){
		auditFlowService.startProcessOfAuditFlow();
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data("startProcessOfAuditFlow")
                 .build().toString();
	}
	/**
	 * 请求路径：http://localhost:33002/xunyi-activiti/action/startProcessOfLeaveSayHello
	 * @Title:        startProcessOfLeaveSayHello 
	 * @Description:  
	 * 简单示例
	 * 节点，线-->事件监听器：全局监听器：ExecutionListener，任务监听器TaskListener
	 * ServiceTask 执行，绑定javaClass，实现接口 javaDelegate
	 * 
	 * 
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月9日 下午5:11:10
	 */
	@RequestMapping(value = "/startProcessOfLeaveSayHello", method = RequestMethod.GET)
	public String startProcessOfLeaveSayHello(HttpServletRequest request){
		leaveSayHelloService.startProcessOfLeaveSayHello();
		 return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data("startProcessOfLeaveSayHello")
                 .build().toString();
	}
}
