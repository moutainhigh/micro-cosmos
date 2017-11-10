package com.yichen.cosmos.cloud.platform.bean;

/**
 * Created by thomas.su on 2017/10/11 9:41.
 */
public class Datasouce {
    private String dsourceId;
    private String dsourceCode;
    private String dsourceName;
    private String dataSource;
    private String dsStatus;
    private String callParams;
    private String callUrl;
    private int flag;
    private String createTime;
    private String updateTime;
    private String orgId;
    private int systemFixed;

    public String getDsourceId() {
        return dsourceId;
    }

    public void setDsourceId(String dsourceId) {
        this.dsourceId = dsourceId;
    }

    public String getDsourceCode() {
        return dsourceCode;
    }

    public void setDsourceCode(String dsourceCode) {
        this.dsourceCode = dsourceCode;
    }

    public String getDsourceName() {
        return dsourceName;
    }

    public void setDsourceName(String dsourceName) {
        this.dsourceName = dsourceName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDsStatus() {
        return dsStatus;
    }

    public void setDsStatus(String dsStatus) {
        this.dsStatus = dsStatus;
    }

    public String getCallParams() {
        return callParams;
    }

    public void setCallParams(String callParams) {
        this.callParams = callParams;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getSystemFixed() {
        return systemFixed;
    }

    public void setSystemFixed(int systemFixed) {
        this.systemFixed = systemFixed;
    }
}
