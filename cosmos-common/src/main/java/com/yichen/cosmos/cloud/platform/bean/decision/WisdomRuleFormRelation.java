package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则、表单关联
 * Created by thomas on 2017/3/7.
 */
public class WisdomRuleFormRelation implements Serializable {
    private static final long serialVersionUID = -1534316539550836555L;

    /**
     * 关联id
     */
    private String ruleRelationId;
    /**
     * 规则id
     */
    private String wisdomRuleId;
    /**
     * 表单id
     */
    private String formId;
    /**
     * 1:有效 0：无效
     */
    private Integer flag;

    private Date createTime;
    private Date updateTime;

    public String getRuleRelationId() {
        return ruleRelationId;
    }

    public void setRuleRelationId(String ruleRelationId) {
        this.ruleRelationId = ruleRelationId;
    }

    public String getWisdomRuleId() {
        return wisdomRuleId;
    }

    public void setWisdomRuleId(String wisdomRuleId) {
        this.wisdomRuleId = wisdomRuleId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
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
