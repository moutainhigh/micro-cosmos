package com.xunyi.cloud.wisdom.activiti.delegete;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by thomas.su on 2018/2/27 17:40.
 */
public class SystemAuditDelegete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("system audit..............");
    }
}
