package com.xunyi.cloud.wisdom.activiti.dao;


import com.xunyi.cloud.wisdom.activiti.model.RmsFactorInfo;

import java.util.List;

public interface RmsFactorInfoDao {
    int deleteByPrimaryKey(Integer id);
    int insert(RmsFactorInfo record);
    int insertSelective(RmsFactorInfo record);
    RmsFactorInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(RmsFactorInfo record);
    int updateByPrimaryKey(RmsFactorInfo record);

    List<RmsFactorInfo> selectAllFactors();
}