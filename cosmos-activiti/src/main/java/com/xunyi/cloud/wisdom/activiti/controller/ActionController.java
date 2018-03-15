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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xunyi.cloud.wisdom.activiti.service.AuditFlowService;
import com.xunyi.cloud.wisdom.activiti.service.LeaveSayHelloService;
import com.xunyi.cloud.wisdom.activiti.service.chapter4.Chapter4Service;
import com.xunyi.cloud.wisdom.activiti.service.chapter4.NativeService;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;

/**
 * 执行工作流
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
	@Autowired
	private Chapter4Service chapter4Service;
	@Autowired
	private NativeService nativeService;
	
	
	@RequestMapping(value = "/nativeInfo", method = RequestMethod.GET)
	public String nativeInfo(HttpServletRequest request){
		nativeService.nativeInfo();
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getPathInfo())
                .build().toString();
	}
	
	
	
	/**
	 * 请求路径http://localhost:33002/xunyi-activiti/action/startProcessOfSplitTest?day=2
	 * @Title:        startProcessOfSplitTest 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @param day
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月13日 下午2:31:07
	 */
	@RequestMapping(value = "/startProcessOfSplitTest", method = RequestMethod.GET)
	public String startProcessOfSplitTest(HttpServletRequest request,@RequestParam("day")Integer day){
		chapter4Service.startProcessOfSplitTest(day);
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getPathInfo())
                .build().toString();
	}
	
	
	
	
	/**
	 * 请求路径http://localhost:33002/xunyi-activiti/action/startProcessOfReceiveTask
	 * @Title:        startProcessOfReceiveTask 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月13日 上午10:52:08
	 * 
	 * 接收任务测试
	 */
	@RequestMapping(value = "/startProcessOfReceiveTask", method = RequestMethod.GET)
	public String startProcessOfReceiveTask(HttpServletRequest request){
		chapter4Service.startProcessOfReceiveTask();
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getPathInfo())
                .build().toString();
	}
	
	
	
	/**
	 * 
	 * @Title:        startProcessOfTimeCycle 
	 * @Description:  
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author  	<a href="1165030287@qq.com">thomas</a>     
	 * @Date 		2018年3月12日 上午10:59:40
	 * 
	 * 异常1：时间格式错误
	 *   Failed to parse cron expression: R50/201803012T100000+08/P0Y0M0DT0H05M0S
	 *   例如：	Invalid format: "201803012T110300" is malformed at "0100"
	 *   		Invalid format: "20180312T110700+08" is malformed at "0700+08"
	 *   
	 *   解决方案：
	 *   定时器时间需要比当前时间【晚】；时间格式错误
	 *   可以修改为 cron表达式：0/5 * * * * ? 
	 *   ISO8601时间格式可以写在注明 要求iso8601的地方，如 Time date(ISO8601) 或者 Time duration
	 *   
	 * 异常2：  0/5 * * * * ? 或者  R3/PT5S
	 *    没有循环执行
	 *    解决方案：
	 *    在 riskcontrol-activiti.xml配置文件中，【将 jobExecutorActivate 设置为 true】
	 *    详情请参照：http://sdywcd.iteye.com/blog/1915467
	 *    最后一句：定时器只会在工作的执行者是可用的状态下被点燃。
	 *    （也就是说，jobExecutorActivate 需要在activiti.cfg.xml中设置成true，如果这个job executor默认是不可用的）
	 *    
	 *    
	 * >>>> 3.    【将 jobExecutorActivate 设置为 true】 时，系统启动时，则会扫描定时器任务表，执行任务
	 * 
	 */
	@RequestMapping(value = "/startProcessOfTimeCycle", method = RequestMethod.GET)
	public String startProcessOfTimeCycle(HttpServletRequest request){
		chapter4Service.startProcessOfTimeCycle();
		return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
				 .data(request.getPathInfo())
                .build().toString();
	}
	
	
	
	
	
	/**
	 * 请求路径：http://localhost:33002/xunyi-activiti/action/startProcessOfAuditFlow
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
				 .data(request.getPathInfo())
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
				 .data(request.getPathInfo())
                 .build().toString();
	}
}
