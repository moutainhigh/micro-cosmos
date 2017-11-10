package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则执行类型
 * Created by thomas on 2017/3/7.
 */
public class RuleExecuteType implements Serializable {
    private static final long serialVersionUID = 6111992865355431508L;
    private Integer id;
    /**
     * 名称
     */
    private String ruleExecuteName;
    /**
     * 类型
     */
    private String ruleExecuteType;
    /**
     * 1:有效 0:无效
     */
    private Integer type;
    private Date createTime;
    private Date updateTime;

    public String getRuleExecuteName() {
        return ruleExecuteName;
    }

    public void setRuleExecuteName(String ruleExecuteName) {
        this.ruleExecuteName = ruleExecuteName;
    }

    public String getRuleExecuteType() {
        return ruleExecuteType;
    }

    public void setRuleExecuteType(String ruleExecuteType) {
        this.ruleExecuteType = ruleExecuteType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
