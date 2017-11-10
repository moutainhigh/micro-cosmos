package com.yichen.cosmos.cloud.platform.rabbit.dao;


import com.yichen.cosmos.cloud.platform.bean.Orgnization;
import com.yichen.cosmos.cloud.platform.bean.User;

import java.util.Map;

/**
 * Created by thomas on 2017/4/25 13:48.
 */
public interface UserRedisDao {

    User getUserById(String userId);

    Orgnization getOrgById(Map<String, String> orgMap);
}
