package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 规则
 * Created by thomas on 2017/3/7.
 */
public class WisdomRule implements Serializable {
    private static final long serialVersionUID = -3054234520551872845L;

    /**
     * 规则条件集合
     */
    private List<WisdomRuleCondition> ruleConditionList;

    /**
     * 规则决策集合
     */
    private List<WisdomRuleDecision> ruleDecisionList;

    /**
     * 规则id
     */
    private String wisdomRuleId;
    /**
     * 规则组id
     */
    @Deprecated
    private String ruleGroupId;

    /**
     * 规则集id
     */
    private String ruleSetId;
    /**
     * 1:有效 0：无效
     */
    private Integer flag;
    private Date createTime;
    private Date updateTime;


    private String creatorId;
    private String updaterId;
    private String orgId;
    private String versionId;

    private String salience;
    private String ruleName;

    public String getRuleSetId() {
        return ruleSetId;
    }

    public void setRuleSetId(String ruleSetId) {
        this.ruleSetId = ruleSetId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getSalience() {
        return salience;
    }

    public void setSalience(String salience) {
        this.salience = salience;
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

    public List<WisdomRuleCondition> getRuleConditionList() {
        return ruleConditionList;
    }

    public void setRuleConditionList(List<WisdomRuleCondition> ruleConditionList) {
        this.ruleConditionList = ruleConditionList;
    }

    public List<WisdomRuleDecision> getRuleDecisionList() {
        return ruleDecisionList;
    }

    public void setRuleDecisionList(List<WisdomRuleDecision> ruleDecisionList) {
        this.ruleDecisionList = ruleDecisionList;
    }

    public String getWisdomRuleId() {
        return wisdomRuleId;
    }

    public void setWisdomRuleId(String wisdomRuleId) {
        this.wisdomRuleId = wisdomRuleId;
    }

    @Deprecated
    public String getRuleGroupId() {
        return ruleGroupId;
    }

    @Deprecated
    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
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
}
