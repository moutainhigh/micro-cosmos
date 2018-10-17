package com.yichen.cosmos.cloud.platform.listener;

import com.yichen.cosmos.cloud.platform.service.SpringContextHelper;
import com.yichen.cosmos.cloud.platform.service.TestService001;
import org.activiti.bpmn.model.Task;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.TransactionDependentTaskListener;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yichen.cosmos.cloud.platform.constants.CommonConstants.oneTime;

/**
 * @Author:thomas su
 * @Date: 2018/8/31 16:32
 * @Description:
 */
public class CustomTransactionTaskListener implements TransactionDependentTaskListener {
    private static final Logger logger = LoggerFactory.getLogger(CustomTransactionTaskListener.class);

    @Override
    public void notify(String processInstanceId, String executionId, Task task, Map<String, Object> executionVariables, Map<String, Object> customPropertiesMap) {
        logger.warn("CustomTransactionTaskListener > 自定义事务监听器.taskName:{}。oneTime：{}",task.getName(),oneTime.get());

        //监听节点创建事件：TaskListener.EVENTNAME_CREATE
        if(oneTime.compareAndSet(false,true)){///1.注释该行，流程一次性全部执行
            // 问题：为什么流程部署时，会触发该监听器？？？？（如果存在未结束的流程时）



            new Thread(new Runnable() {
                @Override
                public void run() {
                    TestService001 testService001 = (TestService001) SpringContextHelper.getBean("testService001");
                    Map<String,Object> params = new HashMap<>();
                    //模拟脏读，防止死循环，只执行一次
                    logger.warn("模拟脏读，防止死循环，只执行一次.taskKey:{},oneTime：{}",task.getId(),oneTime.get());

                    TaskService taskService = (TaskService) SpringContextHelper.getBean("taskService");
                    List<org.activiti.engine.task.Task> activitiTaskList = taskService.createTaskQuery().taskDefinitionKey(task.getId()).list();
                    if(CollectionUtils.isNotEmpty(activitiTaskList)){
                        logger.warn("模拟脏读》taskId:{}",activitiTaskList.get(0).getId());
                        Map result = testService001.completeTask(activitiTaskList.get(0).getId(),params,"");
                    }
                }
            }).start();
        }
    }
}
