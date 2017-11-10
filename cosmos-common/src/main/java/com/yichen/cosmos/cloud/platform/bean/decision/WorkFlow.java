package com.yichen.cosmos.cloud.platform.bean.decision;


import com.yichen.cosmos.cloud.platform.enums.CreditWisdomEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 工作流节点
 * Created by thomas on 2017/3/7.
 */
public class WorkFlow implements Serializable {
    private static final long serialVersionUID = -2172434968057477796L;

    /**
     * 工作流id
     */
    private String workFlowId;
    /**
     * 版本id
     */
    private String versionId;
    /**
     * 工作流名称
     */
    private String flowName;

    /**
     * 1:开始节点  2:终止节点  3:普通节点  {@link CreditWisdomEnum}
     */
    private Integer nodeType;

    /**
     * 工作流节点x坐标
     */
    private Integer xCoordinate;
    /**
     * 工作流节点y坐标
     */
    private Integer yCoordinate;

    private String remark;
    /**
     * 1:有效 0:无效
     */
    private Integer flag;

    private Date createTime;
    private Date updateTime;

    private List<WorkFlowLine> workFlowLineList;

    private String creatorId;
    private String updaterId;
    private String orgId;

    public String getCreatorId() {
        return creatorId;
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

    public List<WorkFlowLine> getWorkFlowLineList() {
        return workFlowLineList;
    }

    public void setWorkFlowLineList(List<WorkFlowLine> workFlowLineList) {
        this.workFlowLineList = workFlowLineList;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
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

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
