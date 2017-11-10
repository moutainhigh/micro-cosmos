package com.yichen.cosmos.cloud.platform.bean.data.center;

import java.io.Serializable;

/**
 * 数据中心，表单中引用的导入项
 * Created by thomas on 2017/3/2.
 */
public class WisdomImport implements Serializable {
    private static final long serialVersionUID = 5165623692316291439L;
    private String importId;
    /**
     * 所属于的表单id
     */
    private String formId;
    /**
     * 导入的包名称
     */
    private String importName;
    /**
     * 导入的包、引用的类全路径
     */
    private String importPath;
    /**
     * 1：有效  0：无效
     */
    private Integer flag;
    /**
     * 备注
     */
    private String remark;

    private String createTime;
    private String updateTime;

    private String creatorId;
    private String updaterId;
    private String orgId;

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

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }

    public String getImportPath() {
        return importPath;
    }

    public void setImportPath(String importPath) {
        this.importPath = importPath;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
