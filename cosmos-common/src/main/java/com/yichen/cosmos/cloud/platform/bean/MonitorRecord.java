package com.yichen.cosmos.cloud.platform.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by hzzt on 2017/9/20.
 * 监控规则，命中信息
 */
public class MonitorRecord {
    /**
     * 命中的表单字段
     */
    private String formId;
    private String fieldId;
    private String versionId;
    private Date createTime;

    public MonitorRecord() {
    }

    public MonitorRecord(String formId, String fieldId, String versionId) {
        this.formId = formId;
        this.fieldId = fieldId;
        this.versionId = versionId;
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


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
