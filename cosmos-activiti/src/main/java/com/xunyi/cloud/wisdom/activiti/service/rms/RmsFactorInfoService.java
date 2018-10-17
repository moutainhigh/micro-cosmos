package com.xunyi.cloud.wisdom.activiti.service.rms;

import com.xunyi.cloud.wisdom.activiti.dao.RmsFactorInfoDao;
import com.xunyi.cloud.wisdom.activiti.model.RmsFactorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RmsFactorInfoService {
    @Autowired
    private RmsFactorInfoDao rmsFactorInfoDao;

    public List<RmsFactorInfo> selectAllFactors(){
        return rmsFactorInfoDao.selectAllFactors();
    }
}