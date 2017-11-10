package com.yichen.cosmos.cloud.platform.bean.gateway;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构账户表
 *
 * @author LinQ
 */
public class OrganizationAccount implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2024865219366650172L;

    private String accountId;

    private String username;

    private String password;

    private String orgId;

    private String accountPid;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private Date resetTime;

    private String memo;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getAccountPid() {
        return accountPid;
    }

    public void setAccountPid(String accountPid) {
        this.accountPid = accountPid == null ? null : accountPid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}