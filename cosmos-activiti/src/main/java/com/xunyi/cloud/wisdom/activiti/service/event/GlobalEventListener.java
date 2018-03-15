package com.xunyi.cloud.wisdom.activiti.service.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月13日 下午3:43:55 
 * @Description
 */
public class GlobalEventListener implements ActivitiEventListener{

	private static final Logger logger = LoggerFactory.getLogger(GlobalEventListener.class);
	
	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.activiti.engine.delegate.event.ActivitiEvent)
	 */
	@Override
	public void onEvent(ActivitiEvent event) {
		logger.info("流程事件信息：");
		logger.info("流程定义：{}",event.getProcessDefinitionId());
		logger.info("流程实例：{}",event.getProcessInstanceId());
		logger.info("流程执行ID：{}",event.getExecutionId());
		logger.info("操作：{}",event.getType());
		
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
         		
		}
		
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException()
	 */
	@Override
	public boolean isFailOnException() {
		return false;
	}

}
