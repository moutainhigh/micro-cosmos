package com.tairanchina.fd.loan.entity;

import java.util.Date;

public class AsyncCallbackRecord {
    /**
     * 
     */
    private Long id;

    /**
     * 原始url
     */
    private String sourceUrl;

    /**
     * 对source_url和postdata进行重复唯一校验码生成
     */
    private String checkRepeatCode;

    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 重试次数
     */
    private Byte tryCount;

    /**
     * 处理状态：等待中，WAITING 处理中 DEALING 完成    COMPLETE 失败    FAILURE
     */
    private String dealState;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 业务：机构ID
     */
    private String orgId;

    /**
     * 业务：策略版本号ID
     */
    private String versionId;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 原始url
     * @return source_url 原始url
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 原始url
     * @param sourceUrl 原始url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    /**
     * 对source_url和postdata进行重复唯一校验码生成
     * @return check_repeat_code 对source_url和postdata进行重复唯一校验码生成
     */
    public String getCheckRepeatCode() {
        return checkRepeatCode;
    }

    /**
     * 对source_url和postdata进行重复唯一校验码生成
     * @param checkRepeatCode 对source_url和postdata进行重复唯一校验码生成
     */
    public void setCheckRepeatCode(String checkRepeatCode) {
        this.checkRepeatCode = checkRepeatCode == null ? null : checkRepeatCode.trim();
    }

    /**
     * 回调地址
     * @return callback_url 回调地址
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * 回调地址
     * @param callbackUrl 回调地址
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    /**
     * 重试次数
     * @return try_count 重试次数
     */
    public Byte getTryCount() {
        return tryCount;
    }

    /**
     * 重试次数
     * @param tryCount 重试次数
     */
    public void setTryCount(Byte tryCount) {
        this.tryCount = tryCount;
    }

    /**
     * 处理状态：等待中，WAITING 处理中 DEALING 完成    COMPLETE 失败    FAILURE
     * @return deal_state 处理状态：等待中，WAITING 处理中 DEALING 完成    COMPLETE 失败    FAILURE
     */
    public String getDealState() {
        return dealState;
    }

    /**
     * 处理状态：等待中，WAITING 处理中 DEALING 完成    COMPLETE 失败    FAILURE
     * @param dealState 处理状态：等待中，WAITING 处理中 DEALING 完成    COMPLETE 失败    FAILURE
     */
    public void setDealState(String dealState) {
        this.dealState = dealState == null ? null : dealState.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 业务：机构ID
     * @return org_id 业务：机构ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 业务：机构ID
     * @param orgId 业务：机构ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 业务：策略版本号ID
     * @return version_id 业务：策略版本号ID
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * 业务：策略版本号ID
     * @param versionId 业务：策略版本号ID
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }
}