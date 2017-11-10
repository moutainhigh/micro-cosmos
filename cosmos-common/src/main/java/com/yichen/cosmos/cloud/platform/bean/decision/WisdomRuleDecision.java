package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则决策 then
 * Created by thomas on 2017/3/8.
 */
public class WisdomRuleDecision implements Serializable {
    private static final long serialVersionUID = 878476266221361091L;

    /**
     * 规则具体条件、决策id
     */
    private String decisionId;
    /**
     * 规则id
     */
    private String wisdomRuleId;
    /**
     * 规则、表单关联id
     */
    private String ruleRelationId;
    /**
     * 表单字段名称
     */
    private String field;
    private String fieldId;
    private String formId;
    //决策表中使用，表单名称
    private String formName;


    private String operator;
    private String judgeValue;
    private Integer flag;
    private Date createTime;
    private Date updateTime;

    private String creatorId;
    private String updaterId;
    private String orgId;

    //同一条规则下，排列序号，值越小越靠前.默认值为0
    private Integer cindex;


    /**
     * 公式
     **/
    private String formula;


    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
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

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public String getWisdomRuleId() {
        return wisdomRuleId;
    }

    public void setWisdomRuleId(String wisdomRuleId) {
        this.wisdomRuleId = wisdomRuleId;
    }

    public String getRuleRelationId() {
        return ruleRelationId;
    }

    public void setRuleRelationId(String ruleRelationId) {
        this.ruleRelationId = ruleRelationId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getJudgeValue() {
        return judgeValue;
    }

    public void setJudgeValue(String judgeValue) {
        this.judgeValue = judgeValue;
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

    public Integer getCindex() {
        return cindex;
    }

    public void setCindex(Integer cindex) {
        this.cindex = cindex;
    }
}
