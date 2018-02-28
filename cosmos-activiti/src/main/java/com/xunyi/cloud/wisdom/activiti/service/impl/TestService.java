package com.xunyi.cloud.wisdom.activiti.service.impl;

import com.xunyi.cloud.wisdom.activiti.dao.TestMapper;
import com.xunyi.cloud.wisdom.activiti.model.Test;
import com.xunyi.cloud.wisdom.activiti.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
}
