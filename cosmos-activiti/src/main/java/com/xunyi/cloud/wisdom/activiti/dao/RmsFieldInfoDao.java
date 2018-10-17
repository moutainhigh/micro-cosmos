package com.xunyi.cloud.wisdom.activiti.dao;


import com.xunyi.cloud.wisdom.activiti.model.RmsFieldInfo;

public interface RmsFieldInfoDao {
    int deleteByPrimaryKey(Integer id);
    int insert(RmsFieldInfo record);
    int insertSelective(RmsFieldInfo record);
    RmsFieldInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(RmsFieldInfo record);
    int updateByPrimaryKey(RmsFieldInfo record);
}