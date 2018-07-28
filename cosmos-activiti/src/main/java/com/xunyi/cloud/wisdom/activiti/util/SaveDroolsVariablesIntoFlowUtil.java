package com.xunyi.cloud.wisdom.activiti.util;

import com.xunyi.cloud.wisdom.activiti.service.activitidrools.SpringContextHelper;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:thomas
 * @Date: 2018/7/13 18:58
 * @Description:
 */
public class SaveDroolsVariablesIntoFlowUtil {

    private static final Logger logger = LoggerFactory.getLogger(SaveDroolsVariablesIntoFlowUtil.class);

    public static void saveVariableIntoFlow(Object processInstanceId,Object key,Object value){
        try{
            System.out.println("[drools]调用，将k-v放入runtimeServce；processInstanceId："+processInstanceId+",  key:"+key+",value= "+value);
            logger.warn("[drools]调用，将k-v放入runtimeServce；processInstanceId:{},key:{},value:{}",processInstanceId,key,value);
            RuntimeService runtimeService =  (RuntimeService) SpringContextHelper.getBean("runtimeService");
            runtimeService.setVariable(String.valueOf(processInstanceId),String.valueOf(key),value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
