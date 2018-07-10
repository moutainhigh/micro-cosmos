package com.tairan.activiti.demo.dynamic.listeners;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月13日 下午3:43:55 
 * @Description
 */
public class TestGlobalEventListener implements ActivitiEventListener,ExecutionListener,TaskListener{

	private static final Logger logger = LoggerFactory.getLogger(TestGlobalEventListener.class);
	
	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.activiti.engine.delegate.event.ActivitiEvent)
	 */
	@Override
	public void onEvent(ActivitiEvent event) {
		/*System.out.println("流程事件信息：");
		System.out.println("流程定义：" + event.getProcessDefinitionId());
		System.out.println("流程实例：" + event.getProcessInstanceId());
		System.out.println("流程执行ID：" + event.getExecutionId());
		System.out.println("操作：" + event.getType());

		System.out.println("捕获事件类型："+event.getType().name()+"type="+ToStringBuilder.reflectionToString(event));
		
		
		ActivitiEventType eventType = event.getType();
		
		//事件类型  {@link ActivitiEventType}
		switch(eventType){
		 case ENGINE_CREATED:
			 System.out.println("流程初始化");
             break;
         case ENGINE_CLOSED:
			 System.out.println("流程关闭");
             break;
         default:
			 System.out.println("event type name:"+event.getType().name());
         		
		}*/


		System.out.println("操作：" + event.getType());
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException()
	 */
	@Override
	public boolean isFailOnException() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		String taskId = delegateTask.getId();
		String taskName = delegateTask.getName();
		logger.info("task id={},task name={}",taskId,taskName);
		System.out.println("[taskId]:"+taskId+", [taskName]:"+taskName);
		
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		String executionId = execution.getId();
		String eventName = execution.getEventName();
		logger.info("executionId={},eventName={}",executionId,eventName);
		System.out.println("[executionId]:"+executionId+", [eventName]:"+eventName);
	}

}
