package com.xunyi.cloud.wisdom.activiti.delegete;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by thomas.su on 2018/2/27 17:41.
 */
public class BizAuditDelegete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("biz  audit ............");
    }
}
