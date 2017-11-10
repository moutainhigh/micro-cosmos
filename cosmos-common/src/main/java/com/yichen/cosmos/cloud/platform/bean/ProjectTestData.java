package com.yichen.cosmos.cloud.platform.bean;


import com.yichen.cosmos.cloud.platform.util.SUID;

import java.util.Date;
import java.util.Map;

/**
 * Created by hzsj on 2017/6/23.
 * 用户上传测试数据记录
 */
public class ProjectTestData {

    private String testId;
    private String userId;
    private String projectId;
    private String projectName;
    private String status;
    private String versionA;
    private String versionAId;
    private String versionB;
    private String versionBId;
    private String orgId;
    private String createId;
    private String updateId;
    private Date updateTime;
    private Date createTime;
    private String outCollection;
    private String personName;
    private String personIdCard;
    private String personPhone;
    private String fileName;

    public void initData(User user, Map<String, String> versionInfoMap, String projectId, String projectName) throws Exception {
        this.testId = SUID.getUUID();
        String v = "";
        String id = "";
        for (Map.Entry<String, String> entry : versionInfoMap.entrySet()) {
            v += entry.getKey() + ",";
            id += entry.getValue() + ",";
        }
        String[] vs = v.split(",");
        String[] ids = id.split(",");
        if (1 == vs.length) {
            versionA = vs[0];
            versionAId = ids[0];
        } else {
            versionA = vs[0];
            versionB = vs[1];
            versionAId = ids[0];
            versionBId = ids[1];
        }
        this.createId = user.getUserId();
        this.userId = user.getUserId();
        this.orgId = user.getOrgId();
        this.projectId = projectId;
        this.projectName = projectName;
        this.createTime = new Date();
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersionA() {
        return versionA;
    }

    public void setVersionA(String versionA) {
        this.versionA = versionA;
    }

    public String getVersionB() {
        return versionB;
    }

    public void setVersionB(String versionB) {
        this.versionB = versionB;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersionAId() {
        return versionAId;
    }

    public void setVersionAId(String versionAId) {
        this.versionAId = versionAId;
    }

    public String getVersionBId() {
        return versionBId;
    }

    public void setVersionBId(String versionBId) {
        this.versionBId = versionBId;
    }

    public String getOutCollection() {
        return outCollection;
    }

    public void setOutCollection(String outCollection) {
        this.outCollection = outCollection;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
