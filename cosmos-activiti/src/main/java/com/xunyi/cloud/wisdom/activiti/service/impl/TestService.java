package com.xunyi.cloud.wisdom.activiti.service.impl;

import com.xunyi.cloud.wisdom.activiti.dao.TestMapper;
import com.xunyi.cloud.wisdom.activiti.model.Test;
import com.xunyi.cloud.wisdom.activiti.service.DemoService;
import com.xunyi.cloud.wisdom.activiti.service.ITestService;
import com.xunyi.cloud.wisdom.activiti.service.activitidrools.SpringContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas.su on 2018/2/27 17:22.
 */
@Service("testService")
public class TestService implements ITestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public Test getTestById(Integer id) {
        Assert.notNull(id);
        return testMapper.selectByPrimaryKey(id);

    }
    private static final Map<String,String> testMap = new HashMap<>();
    static{
        testMap.put("test", DemoService.class.getSimpleName());
    }

    @Override
    public void testName() {
        String serviceName = testMap.get("test");
        Object obj = SpringContextHelper.getBean(serviceName);
        System.out.println("*******************************");

        System.out.println("obj:"+obj);

        System.out.println("*******************************");
    }

    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
}
