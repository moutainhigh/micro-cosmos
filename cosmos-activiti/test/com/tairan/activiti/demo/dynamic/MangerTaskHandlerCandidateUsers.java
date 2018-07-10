package com.tairan.activiti.demo.dynamic;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Arrays;

/**
 * @Author:thomas
 * @Date: 2018/6/28 18:16
 * @Description:
 */
public class MangerTaskHandlerCandidateUsers extends ActivitiListener implements TaskListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("进入MangerTaskHandlerCandidateUsers=========");
        /**从新查询当前用户，再获取当前用户对应的领导*/
        //当前用户
        String[] empLoyees = {"冯小刚经纪人","范冰冰经纪人","冯小刚"};
        delegateTask.addCandidateUsers(Arrays.asList(empLoyees));
        System.out.println("节点任务人========冯小刚经纪人,范冰冰经纪人,冯小刚");

    }
}
