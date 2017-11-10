package com.yichen.cosmos.cloud.platform.bean.data.center;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2017/3/2.
 */
public class WisdomForm implements Serializable {
    private static final long serialVersionUID = -2197107721110081575L;

    private List<WisdomField> wisdomFieldList = new ArrayList<WisdomField>();
    private List<WisdomImport> wisdomImportList = new ArrayList<WisdomImport>();

    private String formId;
    /**
     * 中文名
     **/
    private String chineseName;
    /**
     * 英文名
     */
    private String englishName;
    /**
     * 包路径
     */
    private String packagePath;
    /**
     * 包名称
     */
    private String packageName;

    /**
     * 所属项目id
     */
    private String versionId;

    /**
     * 创建者id
     */
    private String creatorId;

    /**
     * wisdom_form_relation
     * 1:是数组 0：不是数组;表明所关联的表单是否为数组
     */
    private Integer isArray;
    /**
     * 1：有效 0：无效
     */
    private Integer flag;

    /**
     * 1:系统自带数据，不允许修改; 0:非系统自带数据，允许修改
     */
    private String systemFixed;

    /**
     * 1:系统内置表单 2:自定义表单
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    private String createTime;
    private String updateTime;

    private String updaterId;
    private String orgId;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsArray() {
        return isArray;
    }

    public void setIsArray(Integer isArray) {
        this.isArray = isArray;
    }

    public List<WisdomField> getWisdomFieldList() {
        return wisdomFieldList;
    }

    public void setWisdomFieldList(List<WisdomField> wisdomFieldList) {
        this.wisdomFieldList = wisdomFieldList;
    }

    public List<WisdomImport> getWisdomImportList() {
        return wisdomImportList;
    }

    public void setWisdomImportList(List<WisdomImport> wisdomImportList) {
        this.wisdomImportList = wisdomImportList;
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

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public Integer getFlag() {
        return flag;
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

    public String getSystemFixed() {
        return systemFixed;
    }

    public void setSystemFixed(String systemFixed) {
        this.systemFixed = systemFixed;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
