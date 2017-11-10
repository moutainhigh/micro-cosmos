package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.List;

/**
 * 规则集
 * Created by thomas on 2017/3/7.
 */
public class RuleSet implements Serializable {

    private static final long serialVersionUID = 749849359343733475L;

    /**
     * 规则组列表
     */
    private List<RuleGroup> ruleGroupList;

    private List<WisdomRule> ruleList;
    /**
     * 规则集id
     */
    private String ruleSetId;
    /**
     * 工作流程id
     */
    private String workFlowId;
    /**
     * 规则执行条件
     */
    private Integer ruleExecuteId;
    /**
     * 规则名称
     */
    private String ruleSetName;
    private Integer flag;
    private String createTime;
    private String updateTime;

    private String ruleExecuteName;

    private String creatorId;
    private String updaterId;
    private String orgId;

    private String versionId;

    /**
     * 规则集类型
     * 普通规则DRL(1)、规则表TABLE(2)、规则树TREE(3)、记分卡SCORE_CARD(4)
     */
    private String ruleSetType;

    public String getRuleSetType() {
        return ruleSetType;
    }

    public void setRuleSetType(String ruleSetType) {
        this.ruleSetType = ruleSetType;
    }

    public List<WisdomRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<WisdomRule> ruleList) {
        this.ruleList = ruleList;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

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

    public String getRuleExecuteName() {
        return ruleExecuteName;
    }

    public void setRuleExecuteName(String ruleExecuteName) {
        this.ruleExecuteName = ruleExecuteName;
    }

    public List<RuleGroup> getRuleGroupList() {
        return ruleGroupList;
    }

    public void setRuleGroupList(List<RuleGroup> ruleGroupList) {
        this.ruleGroupList = ruleGroupList;
    }

    public String getRuleSetId() {
        return ruleSetId;
    }

    public void setRuleSetId(String ruleSetId) {
        this.ruleSetId = ruleSetId;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public Integer getRuleExecuteId() {
        return ruleExecuteId;
    }

    public void setRuleExecuteId(Integer ruleExecuteId) {
        this.ruleExecuteId = ruleExecuteId;
    }

    public String getRuleSetName() {
        return ruleSetName;
    }

    public void setRuleSetName(String ruleSetName) {
        this.ruleSetName = ruleSetName;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
