package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目
 * Created by thomas on 2017/2/21.
 */
public class Project implements Serializable {

    private static final long serialVersionUID = 4445854054276505841L;
    private String projectId;
    private String projectName;
    private String creatorId;
    private String projectGroupId;
    private String projectArtifactId;
    private Integer status;
    private Integer flag;
    private String memo;
    private String createTime;
    private String upStringTime;

    private String upStringrId;
    private String orgId;
    private List<ProjectVersion> projectVersionList;

    public String getUpStringrId() {
        return upStringrId;
    }

    public void setUpStringrId(String upStringrId) {
        this.upStringrId = upStringrId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<ProjectVersion> getProjectVersionList() {
        return projectVersionList;
    }

    public void setProjectVersionList(List<ProjectVersion> projectVersionList) {
        this.projectVersionList = projectVersionList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getProjectGroupId() {
        return projectGroupId;
    }

    public void setProjectGroupId(String projectGroupId) {
        this.projectGroupId = projectGroupId;
    }

    public String getProjectArtifactId() {
        return projectArtifactId;
    }

    public void setProjectArtifactId(String projectArtifactId) {
        this.projectArtifactId = projectArtifactId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpStringTime() {
        return upStringTime;
    }

    public void setUpStringTime(String upStringTime) {
        this.upStringTime = upStringTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
