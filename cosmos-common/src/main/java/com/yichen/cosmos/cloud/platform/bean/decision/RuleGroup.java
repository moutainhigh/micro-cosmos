package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 规则组
 * Created by thomas on 2017/3/7.
 */
public class RuleGroup implements Serializable {
    private static final long serialVersionUID = 8211703209298840667L;

    //规则列表
    private List<WisdomRule> wisdomRuleList;
    /**
     * 规则组id
     */
    private String ruleGroupId;
    /**
     * 规则集id
     */
    private String ruleSetId;
    /**
     * 规则组名称
     */
    private String ruleGroupName;
    /**
     * 权重
     */
    private String salience;
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

    public List<WisdomRule> getWisdomRuleList() {
        return wisdomRuleList;
    }

    public void setWisdomRuleList(List<WisdomRule> wisdomRuleList) {
        this.wisdomRuleList = wisdomRuleList;
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
    }

    public String getRuleSetId() {
        return ruleSetId;
    }

    public void setRuleSetId(String ruleSetId) {
        this.ruleSetId = ruleSetId;
    }

    public String getRuleGroupName() {
        return ruleGroupName;
    }

    public void setRuleGroupName(String ruleGroupName) {
        this.ruleGroupName = ruleGroupName;
    }

    public String getSalience() {
        return salience;
    }

    public void setSalience(String salience) {
        this.salience = salience;
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
