package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 工作流线
 * Created by thomas on 2017/3/7.
 */
public class WorkFlowLine implements Serializable {
    private static final long serialVersionUID = 558695642545212416L;

    /**
     * 工作流线条件id
     */
    private String workFlowLineId;
    /**
     * 工作流id
     */
    private String workFlowId;
    /**
     * 条件名称
     */
    private String lineName;
    /**
     * 条件起点
     */
    private String fromFlowId;
    /**
     * 条件终点
     */
    private String toFlowId;
    private Integer flag;
    private Date createTime;
    private Date updateTime;

    private List<WorkFlowLineCondition> conditionList;

    private String versionId;
    private String creatorId;
    private String updaterId;
    private String orgId;

    public String getCreatorId() {
        return creatorId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<WorkFlowLineCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<WorkFlowLineCondition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getWorkFlowLineId() {
        return workFlowLineId;
    }

    public void setWorkFlowLineId(String workFlowLineId) {
        this.workFlowLineId = workFlowLineId;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public String getFromFlowId() {
        return fromFlowId;
    }

    public void setFromFlowId(String fromFlowId) {
        this.fromFlowId = fromFlowId;
    }

    public String getToFlowId() {
        return toFlowId;
    }

    public void setToFlowId(String toFlowId) {
        this.toFlowId = toFlowId;
    }
}
