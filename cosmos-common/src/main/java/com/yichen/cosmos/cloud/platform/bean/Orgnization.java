package com.yichen.cosmos.cloud.platform.bean;

/**
 * Created by thomas on 2017/4/26 15:22.
 */
public class Orgnization {

    /**
     * ID主键
     */
    private String orgId;
    /**
     * 公司名
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人号码
     */
    private String contactPhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 推荐人
     */
    private String referrer;
    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 营业执照号码
     */
    private String businessLicenseNo;
    /**
     * 法人
     */
    private String corporate;
    /**
     * 所属行业ID 对应cred_industry表
     */
    private String industryId;
    /**
     * 机构审核状态(通过:PASS;拒绝:REFUSED;待审核:AUDIT;跟进:FOLLOW)
     */
    private String status;
    /**
     * 账号是否锁定 (正常:OK 锁定LOCKED)
     */
    private String islock;
    /**
     * 审核备注(保留最后一次备注)
     */
    private String auditRecord;
    /**
     * 组织机构代码证(文件存储名称)
     */
    private String organizationCodeUrl;
    /**
     * 税务登记证(文件存储名称)
     */
    private String taxProveUrl;
    /**
     * 营业执照(文件存储名称)
     */
    private String businessLicenseUrl;
    /**
     * 运营者身份证照片正面
     */
    private String idcardPositiveUrl;
    /**
     * 运营者身份证照片反面
     */
    private String idcardReverseUrl;
    /**
     * 机构有效开始时间
     */
    private String startTime;
    /**
     * 机构有效结束时间
     */
    private String endTime;
    /**
     * 用户其他备注
     */
    private String memo;
    /**
     * 网关密钥，用于加密
     */
    private String parternPrivatekey;
    /**
     * 网关id，网关查询时用该id判断机构用户
     */
    private String parternId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIslock() {
        return islock;
    }

    public void setIslock(String islock) {
        this.islock = islock;
    }

    public String getAuditRecord() {
        return auditRecord;
    }

    public void setAuditRecord(String auditRecord) {
        this.auditRecord = auditRecord;
    }

    public String getOrganizationCodeUrl() {
        return organizationCodeUrl;
    }

    public void setOrganizationCodeUrl(String organizationCodeUrl) {
        this.organizationCodeUrl = organizationCodeUrl;
    }

    public String getTaxProveUrl() {
        return taxProveUrl;
    }

    public void setTaxProveUrl(String taxProveUrl) {
        this.taxProveUrl = taxProveUrl;
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getIdcardPositiveUrl() {
        return idcardPositiveUrl;
    }

    public void setIdcardPositiveUrl(String idcardPositiveUrl) {
        this.idcardPositiveUrl = idcardPositiveUrl;
    }

    public String getIdcardReverseUrl() {
        return idcardReverseUrl;
    }

    public void setIdcardReverseUrl(String idcardReverseUrl) {
        this.idcardReverseUrl = idcardReverseUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getParternPrivatekey() {
        return parternPrivatekey;
    }

    public void setParternPrivatekey(String parternPrivatekey) {
        this.parternPrivatekey = parternPrivatekey;
    }

    public String getParternId() {
        return parternId;
    }

    public void setParternId(String parternId) {
        this.parternId = parternId;
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
}
