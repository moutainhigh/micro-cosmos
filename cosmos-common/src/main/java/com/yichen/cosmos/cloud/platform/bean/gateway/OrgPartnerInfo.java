package com.yichen.cosmos.cloud.platform.bean.gateway;

import java.io.Serializable;
import java.util.List;

/**
 * 机构商户信息
 *
 * @author Administrator
 */
public class OrgPartnerInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7164565376084663367L;


    private String accountId;//主账号id

    private String userName;//主账号姓名

    private String orgId; // 机构id

    private String companyName; // 公司名称

    private List<String> versionIdList; //项目版本

    public List<String> getVersionIdList() {
        return versionIdList;
    }

    public void setVersionIdList(List<String> versionIdList) {
        this.versionIdList = versionIdList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
