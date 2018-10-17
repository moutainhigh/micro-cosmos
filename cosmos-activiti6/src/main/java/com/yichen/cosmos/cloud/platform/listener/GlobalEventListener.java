package com.yichen.cosmos.cloud.platform.listener;

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
	
	/** (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.activiti.engine.delegate.event.ActivitiEvent)
	 * {@link ActivitiEventType}
	 */
	@Override
	public void onEvent(ActivitiEvent event) {
		logger.debug("[全局]捕获事件类型：{},type:{}",event.getType().name(), ToStringBuilder.reflectionToString(event));

	}

	@Override
	public boolean isFailOnException() {
		return false;
	}
}
