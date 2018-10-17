package com.tairanchina.csp.rms.common.dao;

import com.tairanchina.csp.rms.common.entity.rmsFactorInfo;

public interface rmsFactorInfoDao {
    /**
     *
     * @mbg.generated 2018-08-21
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int insert(rmsFactorInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int insertSelective(rmsFactorInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    rmsFactorInfo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int updateByPrimaryKeySelective(rmsFactorInfo record);

    /**
     *
     * @mbg.generated 2018-08-21
     */
    int updateByPrimaryKey(rmsFactorInfo record);
}