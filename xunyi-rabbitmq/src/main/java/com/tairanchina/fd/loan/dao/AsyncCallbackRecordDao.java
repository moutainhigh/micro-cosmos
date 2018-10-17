package com.tairanchina.fd.loan.dao;

import com.tairanchina.fd.loan.entity.AsyncCallbackRecord;
import com.tairanchina.fd.loan.entity.AsyncCallbackRecordWithBLOBs;

public interface AsyncCallbackRecordDao {
    /**
     *
     * @mbg.generated 2018-05-30
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    int insert(AsyncCallbackRecordWithBLOBs record);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    int insertSelective(AsyncCallbackRecordWithBLOBs record);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    AsyncCallbackRecordWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    int updateByPrimaryKeySelective(AsyncCallbackRecordWithBLOBs record);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    int updateByPrimaryKeyWithBLOBs(AsyncCallbackRecordWithBLOBs record);

    /**
     *
     * @mbg.generated 2018-05-30
     */
    int updateByPrimaryKey(AsyncCallbackRecord record);
}