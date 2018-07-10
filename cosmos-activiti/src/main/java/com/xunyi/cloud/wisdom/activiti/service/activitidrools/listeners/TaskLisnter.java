package com.xunyi.cloud.wisdom.activiti.service.activitidrools.listeners;

import com.xunyi.cloud.wisdom.activiti.service.activitidrools.SpringContextHelper;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.TestCommonService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/7/6 14:45
 * @Description:
 */
public class TaskLisnter implements ActivitiEventListener,ExecutionListener,TaskListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskLisnter.class);
    @Override
    public void notify(DelegateExecution execution) throws Exception {

    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskdbId = delegateTask.getId();
        String processInstanceId = delegateTask.getProcessInstanceId();
        Map<String, Object> variables = delegateTask.getVariables();
        String taskName = delegateTask.getName();

        if(TaskListener.EVENTNAME_CREATE.equals(delegateTask.getEventName())){
            logger.info("测试动态绑定监听器 [节点创建]== DelegateTask");
            logger.info("[节点]名称:{}",taskName);

            //可以异步发起操作=============

            //可以调用其它服务
            TestCommonService testCommonService = (TestCommonService)SpringContextHelper.getBean("testCommonService");
            testCommonService.info();
        }
        if(TaskListener.EVENTNAME_CREATE.equals(delegateTask.getEventName())){
            logger.info("测试动态绑定监听器》》》》》》 [节点完成]== DelegateTask");
            logger.info("[节点]名称:{}",taskName);
        }

    }

    @Override
    public void onEvent(ActivitiEvent event) {
        logger.info("测试动态绑定监听器，==== ActivitiEvent");
        logger.info("[节点]事件类型：{}",event.getType().name());
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
