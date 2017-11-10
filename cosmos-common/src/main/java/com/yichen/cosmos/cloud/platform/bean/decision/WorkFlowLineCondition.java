package com.yichen.cosmos.cloud.platform.bean.decision;

import java.io.Serializable;

/**
 * 工作流线条件
 * Created by thomas on 2017/3/7.
 */
public class WorkFlowLineCondition implements Serializable {
    private static final long serialVersionUID = 8919700944811575322L;

    private String flowLineConditionId;

    private String workFlowLineId;
    private String formId;
    private String fieldId;
    private String operator;
    private String judgeValue;
    private String flag;
    private String createTime;
    private String updateTime;

    private String formName;
    private String fieldName;
    private String fieldClassName;

    private String creatorId;
    private String updaterId;
    private String orgId;

    //同一条规则下，排列序号，值越小越靠前.默认值为0
    private Integer cindex;

    //线条件连接符
    private String connector;


    /**
     * 公式
     **/
    private String formula;


    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    public String getFlowLineConditionId() {
        return flowLineConditionId;
    }

    public void setFlowLineConditionId(String flowLineConditionId) {
        this.flowLineConditionId = flowLineConditionId;
    }

    public String getWorkFlowLineId() {
        return workFlowLineId;
    }

    public void setWorkFlowLineId(String workFlowLineId) {
        this.workFlowLineId = workFlowLineId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
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

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldClassName() {
        return fieldClassName;
    }

    public void setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
    }

    public Integer getCindex() {
        return cindex;
    }

    public void setCindex(Integer cindex) {
        this.cindex = cindex;
    }
}
