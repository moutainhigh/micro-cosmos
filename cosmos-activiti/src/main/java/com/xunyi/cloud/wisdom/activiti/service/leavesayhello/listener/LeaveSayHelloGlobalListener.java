package com.xunyi.cloud.wisdom.activiti.service.leavesayhello.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午3:35:00 
 * @Description: 
 * 
 * 第一部分：实现接口 ExecutionListener
 * 1. 全局监听器实现的接口是org.activiti.engine.delegate.ExecutionListener，
 * org.activiti.engine.impl.pvm.delegate.ExecutionListener这个接口新版本已经废弃。
 * 
 * 2. 设置监听器时，指定事件event有三种：start、event、take
 * 
 * 3. 编辑bpmn文件时，Field definition uses unexisting field 'name' 
 *    设置字段名称，必须是存在的
 *    
 * 4. 参照：http://blog.csdn.net/qq_30739519/article/details/51258447 
 * 
 * 5. take 事件，监听器是设置在"线"上  
 * 
 * 第二部分： 实现TaskListener
 *  2.1 节点监听器的定义接口org.activiti.engine.delegate.TaskListener，
 *  	org.activiti.engine.impl.pvm.delegate.TaskListener已经废弃不用。
 *  
 *  2.2  注入： http://blog.csdn.net/u012613903/article/details/43732941
 *  
 *  2.3
 *  
 *  2.4 
 * 
 */
public class LeaveSayHelloGlobalListener implements ExecutionListener,TaskListener{

	private static final Logger logger = LoggerFactory.getLogger(LeaveSayHelloGlobalListener.class);
	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {

		//流程实例start、end、take的时候调用。take是监控连线的时候使用的。
		
		String eventName = execution.getEventName();
		String activityId = execution.getCurrentActivityId();
		//activiti-version>5.18.0
//		String activityName = execution.getCurrentActivityName();
		String activityName = "";
		
		if(EVENTNAME_START.equals(eventName)){
			logger.info("开始节点启动了.....activityId={},activityName={}",activityId,activityName);
			
		}else if(EVENTNAME_END.equals(eventName)){
			logger.info("结束节点启动了.....activityId={},activityName={}",activityId,activityName);
			
		}else if(EVENTNAME_TAKE.endsWith(eventName)){
			logger.info("连线节点启动了.....activityId={},activityName={}",activityId,activityName);
		}else {
			logger.info("....eventName={}.",eventName);
		}
		
		Map<String, Object> variables = execution.getVariables();
		//logger.info("...变量 .variables={}.",JSON.toJSONString(variables));
		
	}
	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {

		//绑定在usertask节点上
		String eventName = delegateTask.getEventName();
		
		switch(eventName){
		case EVENTNAME_CREATE:
				logger.info("任务创建");
				break;
		case EVENTNAME_ASSIGNMENT:
				logger.info("任务分配");
				break;
		case EVENTNAME_COMPLETE:
				logger.info("任务完成");
				break;
		case EVENTNAME_DELETE:
				logger.info("任务删除");
				break;
		case EVENTNAME_ALL_EVENTS:
				logger.info("所有事件");
				break;
		default:
				logger.info("默认设置");
				break;
		}
	}

}
