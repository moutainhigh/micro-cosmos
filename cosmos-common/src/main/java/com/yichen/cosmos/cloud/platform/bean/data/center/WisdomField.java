package com.yichen.cosmos.cloud.platform.bean.data.center;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas on 2017/3/2.
 */
public class WisdomField implements Serializable {

    private static final long serialVersionUID = -275883121315320817L;
    private String fieldId;
    /**
     * 表单id
     */
    private String formId;
    /**
     * 中文名称
     */
    private String chineseName;
    /**
     * 英文名称
     */
    private String englishName;
    /**
     * 类型id
     */
    private String fieldTypeId;

    private String typeName;
    private String typePath;

    /**
     * 1:是数组 0：不是数组
     */
    private Integer isArray = 0;
    /**
     * 1：有效 0：无效
     */
    private Integer flag;
    /**
     * 1:系统自带数据，不允许修改; 0:非系统自带数据，允许修改
     */
    private String systemFixed;
    /**
     * 备注
     */
    private String remark;
    private String createTime;
    private String updateTime;

    private String creatorId;
    private String updaterId;
    private String orgId;

    private String choiceId;
    private List<Map<String, String>> dictionaryList;

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public List<Map<String, String>> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<Map<String, String>> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    public Integer getIsArray() {
        return isArray;
    }

    public void setIsArray(Integer isArray) {
        this.isArray = isArray;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypePath() {
        return typePath;
    }

    public void setTypePath(String typePath) {
        this.typePath = typePath;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getFlag() {
        return flag;
    }

    public String getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(String fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getSystemFixed() {
        return systemFixed;
    }

    public void setSystemFixed(String systemFixed) {
        this.systemFixed = systemFixed;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
