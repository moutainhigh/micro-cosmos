package com.yichen.cosmos.cloud.platform.bean.gateway;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构信息表
 *
 * @author LinQ
 */
public class Organization implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7406021350983980763L;

    private String orgId;

    private String companyName;

    private String contact;

    private String contactPhone;

    private String email;

    private String referrer;

    private String referrerName;

    private String siteName;

    private String companyAddress;

    private String businessLicenseNo;

    private String corporate;

    private String industryId;

    private String status;

    private String islock;

    private String auditRecord;

    private String organizationCodeUrl;

    private String taxProveUrl;

    private String businessLicenseUrl;

    private String idcardPositiveUrl;

    private String idcardReverseUrl;

    private Date startTime;

    private Date endTime;

    private String memo;

    private Date createTime;

    private Date updateTime;

    private String parternId;

    private String parternPrivatekey;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo == null ? null : businessLicenseNo.trim();
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate == null ? null : corporate.trim();
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIslock() {
        return islock;
    }

    public void setIslock(String islock) {
        this.islock = islock == null ? null : islock.trim();
    }

    public String getAuditRecord() {
        return auditRecord;
    }

    public void setAuditRecord(String auditRecord) {
        this.auditRecord = auditRecord == null ? null : auditRecord.trim();
    }

    public String getOrganizationCodeUrl() {
        return organizationCodeUrl;
    }

    public void setOrganizationCodeUrl(String organizationCodeUrl) {
        this.organizationCodeUrl = organizationCodeUrl == null ? null : organizationCodeUrl.trim();
    }

    public String getTaxProveUrl() {
        return taxProveUrl;
    }

    public void setTaxProveUrl(String taxProveUrl) {
        this.taxProveUrl = taxProveUrl == null ? null : taxProveUrl.trim();
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl == null ? null : businessLicenseUrl.trim();
    }

    public String getIdcardPositiveUrl() {
        return idcardPositiveUrl;
    }

    public void setIdcardPositiveUrl(String idcardPositiveUrl) {
        this.idcardPositiveUrl = idcardPositiveUrl == null ? null : idcardPositiveUrl.trim();
    }

    public String getIdcardReverseUrl() {
        return idcardReverseUrl;
    }

    public void setIdcardReverseUrl(String idcardReverseUrl) {
        this.idcardReverseUrl = idcardReverseUrl == null ? null : idcardReverseUrl.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getParternId() {
        return parternId;
    }

    public void setParternId(String parternId) {
        this.parternId = parternId;
    }

    public String getParternPrivatekey() {
        return parternPrivatekey;
    }

    public void setParternPrivatekey(String parternPrivatekey) {
        this.parternPrivatekey = parternPrivatekey;
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
    }
}