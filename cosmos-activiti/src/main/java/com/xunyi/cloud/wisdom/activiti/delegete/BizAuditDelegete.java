package com.xunyi.cloud.wisdom.activiti.delegete;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * Created by thomas.su on 2018/2/27 17:41.
 * 
 * 三种使用方法解读：javaclass、expression、delegateexpression
 * http://blog.csdn.net/u012613903/article/details/42677635 
 *
 */
public class BizAuditDelegete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("========[biz]========================");
        System.out.println("biz  audit ............");
        Map<String, Object> variables =  execution.getVariables();
        System.out.println(JSON.toJSONString(variables));
        System.out.println("========[biz]========================");
    }
}
