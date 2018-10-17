package com.tairanchina.csp.rms.common.dao;

import com.tairanchina.csp.rms.common.entity.rmsFieldInfo;

public interface rmsFieldInfoDao {
    /**
     *
     * @mbg.generated 2018-08-21
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int insert(rmsFieldInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int insertSelective(rmsFieldInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    rmsFieldInfo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int updateByPrimaryKeySelective(rmsFieldInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int updateByPrimaryKey(rmsFieldInfo record);
}