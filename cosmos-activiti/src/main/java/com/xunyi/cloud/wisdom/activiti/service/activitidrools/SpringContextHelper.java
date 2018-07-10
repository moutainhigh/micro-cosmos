package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextHelper implements ApplicationContextAware {
    
	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHelper.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(ApplicationContext applicationContext)  throws BeansException {
	        return applicationContext;
	}  
	      
	public static Object getBean(String beanName){
		 return applicationContext.getBean(beanName);
	}

    public static <T> Map<String,T> getBeansOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }


}
