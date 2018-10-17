package com.yichen.cosmos.cloud.platform.util;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * Created by thomas.su on 2018/2/27 9:40.
 */
public class UUIDGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
