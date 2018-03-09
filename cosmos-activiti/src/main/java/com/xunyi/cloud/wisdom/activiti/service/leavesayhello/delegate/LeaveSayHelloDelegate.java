/**
 * 
 */
package com.xunyi.cloud.wisdom.activiti.service.leavesayhello.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 如果引用依赖其它实现类的话，可以通过execution-Variables 实现
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月9日 下午2:04:59 
 */
public class LeaveSayHelloDelegate implements JavaDelegate{

	private static final Logger logger = LoggerFactory.getLogger(LeaveSayHelloDelegate.class);
	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.JavaDelegate#execute(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("this is a hello example.");
		
	}

}
