package com.xunyi.cloud.wisdom.activiti.service.rms;

import com.xunyi.cloud.wisdom.activiti.dao.RmsFieldInfoDao;
import com.xunyi.cloud.wisdom.activiti.model.RmsFieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RmsFieldInfoService {

    @Autowired
    private RmsFieldInfoDao rmsFieldInfoDao;

    public void insertSelective(RmsFieldInfo record){
        rmsFieldInfoDao.insertSelective(record);
    }
}