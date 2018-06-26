package com.xunyi.cloud.wisdom.activiti.delegete;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * Created by thomas.su on 2018/2/27 17:40.
 */
public class SystemAuditDelegete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution){
        System.out.println("========[system]========================");
        System.out.println("system audit..............");
        Map<String, Object> variables =  execution.getVariables();
        System.out.println(JSON.toJSONString(variables));
        System.out.println("========[system]========================");
    }
}
