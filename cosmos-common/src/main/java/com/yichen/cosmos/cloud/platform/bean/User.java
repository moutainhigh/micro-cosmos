package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by thomas on 2017/2/21.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5133258194012506161L;

    public User() {
    }

    private String userId;
    private String fatherId;
    private String orgId;
    private String username;
    private String password;
    private String account;
    private String phone;
    private String phoneIsBind;
    private String email;
    private Integer status;
    private Integer flag;
    private String memo;
    private String createTime;
    private String updateTime;
    private String groupId;
    private List<Map> groupList;

    public String getPhoneIsBind() {
        return phoneIsBind;
    }

    public void setPhoneIsBind(String phoneIsBind) {
        this.phoneIsBind = phoneIsBind;
    }

    public List<Map> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Map> groupList) {
        this.groupList = groupList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
