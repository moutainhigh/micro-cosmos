package com.yichen.cosmos.cloud.platform.bean;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 信贷评分模型
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年11月10日 上午10:25:47
 */

public class CreditScore implements Serializable {

    private static final long serialVersionUID = -1254385602436932323L;

    public CreditScore() {
    }

    private Long timestamp = System.currentTimeMillis();

    private String projectName;

    //自动审核:枚举值是：Accept、Reject
    private String autoReviewAdvice;
    //自动审核的原因
    private String autoReviewAdviceDesc;

    public String getAutoReviewAdviceDesc() {
        return autoReviewAdviceDesc;
    }

    public void setAutoReviewAdviceDesc(String autoReviewAdviceDesc) {
        this.autoReviewAdviceDesc = autoReviewAdviceDesc;
    }

    public String getAutoReviewAdvice() {
        return autoReviewAdvice;
    }

    public void setAutoReviewAdvice(String autoReviewAdvice) {
        this.autoReviewAdvice = autoReviewAdvice;
    }

    //身份证号
    private String numberIdCard;
    //
//	报告对象姓名
    private String realnameUser;

    //  报告对象手机号
    private String numberCellphone;

    //是否在黑名单中。true：在    false：不在
    private boolean black;
    //风险等级  参照  CreditRiskEnum.java
    private String riskLevel;
    //是否在金融机构黑名单。true：在  false：不在
    private boolean financyBlack;

    //年龄
    private Integer ageUser;

    //婚姻状况
    private String marriageStatus;

    //学历
    private String degreeDiploma;

    //工作单位性质
    private String propertyCompany;

    //工作部门
    private String departmentCompany;

    //公积金缴存额：蜂贷自动化审核字段-公积金缴存额
    private Double fundDeposit;
    //公积金余额：蜂贷自动化审核字段-公积金余额
    private Double fundBalance;
    //公积金缴存月份：蜂贷自动化审核字段-公积金月份
    private Integer fundNum;
    //工作城市与户籍地是否一致.一致/不一致/无数据：蜂贷自动化审核字段-工作城市与户籍地是否一致
    private String isSameWorkPlace;
    //公积金提取记录.参照 AccumulationFundEnum.java：蜂贷自动化审核字段-公积金提取记录
    private Integer fundExtract;

    //NFCS授信记录:贷款笔数
    private Integer loanCount;

    //NFCS授信记录:总授信额
    private Double loanAmt;

    //NFCS授信使用率
    private Double loanRatio;

    //NFCS单笔最高授信额
    private Double maxCreditAmt;
    //NFCS最早授信时间/首贷日  参照 LoanTimeEnum.java
    private Integer firstLoanTime;


    //NFCS还款记录  参照 PaymentRecordEnum.java
    private Integer paymentRecord;

    //NFCS近6个月内机构查询记录 LoanReportQueryRecordEnum.java
    private Integer loanReportQueryRecord;

    //宜信致诚授信记录.当前授信额度.参照  CreditAmtEnum.java
    private Integer currentCreditAmt;

    //宜信致诚授信记录.当前时间与致诚最早授信时间之差：无/1年内，[0,1）/1-3年，[1,3）/3年以上，[3,+）
    private Integer ealiestCreditTime;
    //宜信致诚授信记录.最早授信时间
    private Integer zcEaliestCreditTime;

    //宜信致诚授信记录.违约概率
    private Double zcScoreRate;

    //宜信致诚授信记录.资料虚假.true:虚假；false：非虚假
    private boolean shamData = false;

    //宜信致诚还款记录.参照  ZCPaymentRecordEnum.java
    private Integer zcPayBackRecord;

    //宜信致诚近6个月内查询记录
    private Integer stasticsTimes;
    //芝麻信用分
    private Integer zmScore;
    //7天内平台申请数量
    private Integer applyPlateform7days;
    //7天内P2P平台申请数量
    private Integer applyP2pPlateform7days;
    //3个月内平台申请数量
    private Integer applyPlateform3M;
    //3个月内P2P平台申请数量
    private Integer applyP2pPlateform3M;

    //聚信立:是否实名:实名认证、非实名认证
    private String realName;

    //聚信立:手机使用时长
    private Integer phoneUsedTime;

    //聚信立:查询过该用户的企业数量
    private Integer searchedOrgCnt;
    //聚信立:电话号码注册过的相关企业(数量)
    private Integer registerOrgCnt;

    //身份证关联的手机号码
    private Integer idcardWithOtherPhones;

    //身份证号关联的其他姓名
    private Integer idcardWithOtherNames;


    //手机号关联的身份证号码
    private Integer phoneWithOtherIdcards;

    //手机号关联的其他姓名
    private Integer phoneWithOtherNames;

    //黑中介分数
    private Integer blackInfoPhoneGrayScore;

    //朋友圈所在地与工作地是否一致.一致/不一致/无数据
    private String workPlaceConsistent;

    //第一联系人排名
    private Integer contact1MobileRank;

    //第二联系人排名
    private Integer contact2MobileRank;

    //贷款类被叫机构数
    private Integer loanOrgCallCnt;

    //贷款类联系机构数
    private Integer contactLoanOrg;

    //贷款类被叫次数
    private Integer loanOrgCallCntTimes;

    //银行类被叫机构数
    private Integer bankLoanOrgCallCnt;

    //银行类被叫次数
    private Integer bankLoanOrgCallCntTimes;

    //信用卡类被叫机构数
    private Integer creditLoanOrgCallCnt;

    //信用卡类被叫次数
    private Integer creditLoanOrgCallCntTimes;

    //律师类联系次数
    private Integer lawyerContactedCnt;

    //110联系次数
    private Integer contacted_110_Cnt;

    //互通电话号码个数
    private Integer eachOtherContactsCnt;

    //近6个月静默天数
    private Integer silentDaysSum;

    //近6个月出行次数
    private Integer tripTimes;
    //蜂贷自动化审核字段-详单数据推算在网时长（phoneUsedTimeByDetails）
    private Integer phoneUsedTimeByDetails;
    //蜂贷自动化审核字段-认证数据推算在网时长（phoneUsedTimeByRegister）
    private Integer phoneUsedTimeByRegister;

    //蜂贷自动化审核字段-芝麻行业风险命中（zmWatchListHitRiskCode）
    private String zmWatchListHitRiskCode;

    //蜂贷自动化审核字段-近一年芝麻行业关注逾期次数（zmWatchListOverdueTimes12Mths）
    private String zmWatchListOverdueTimes12Mths;
    //蜂贷自动化审核字段-近一年芝麻行业关注逾期次数（zmWatchListOverdueTimes24Mths）
    private String zmWatchListOverdueTimes24Mths;

    //--------------------[分数]--------start--------------------------------------
    //年龄
    private Integer ageUserParserScore;
    //婚姻状况
    private Integer marriageStatusParserScore;
    //学历
    private Integer degreeDiplomaParserScore;
    //工作单位性质
    private Integer propertyCompanyParserScore;
    //工作部门
    private Integer departmentCompanyParserScore;

    //是否在黑名单中。true：在    false：不在
    private Integer isBlackParserScore;
    //风险等级  参照  CreditRiskEnum.java
    private Integer riskLevelParserScore;
    //是否在金融机构黑名单。true：在  false：不在
    private Integer isFinancyBlackParserScore;

    //公积金缴存额
    private Integer fundDepositParserScore;
    //公积金余额
    private Integer fundBalanceParserScore;
    //公积金缴存月份
    private Integer fundNumParserScore;
    //工作城市与户籍地是否一致.一致/不一致/无数据
    private Integer isSameWorkPlaceParserScore;
    //公积金提取记录.参照 AccumulationFundEnum.java
    private Integer fundExtractParserScore;

    //NFCS授信记录:贷款笔数
    private Integer loanCountParserScore;

    //NFCS授信记录:总授信额
    private Integer loanAmtParserScore;

    //授信使用率
    private Integer loanRatioParserScore;

    //单笔最高授信额
    private Integer maxCreditAmtParserScore;
    //最早授信时间/首贷日  参照 LoanTimeEnum.java
    private Integer firstLoanTimeParserScore;


    //NFCS还款记录  参照 PaymentRecordEnum.java
    private Integer paymentRecordParserScore;

    //NFCS近6个月内机构查询记录 LoanReportQueryRecordEnum.java
    private Integer loanReportQueryRecordParserScore;

    //宜信致诚授信记录.当前授信额度.参照  CreditAmtEnum.java
    private Integer currentCreditAmtParserScore;

    //宜信致诚授信记录.最早授信时间
    private Integer ealiestCreditTimeParserScore;

    //宜信致诚授信记录.违约概率
    private Integer zcScoreRateParserScore;

    //宜信致诚授信记录.资料虚假.true:虚假；false：非虚假
    private Integer isShamDataParserScore;

    //宜信致诚还款记录
    private Integer zcPayBackRecordParserScore;

    //宜信致诚近6个月内查询记录
    private Integer stasticsTimesParserScore;

    //总分数:综合得分
    private Double reportScore = 0.0;

    //芝麻信用分
    private Integer zmScoreParserScore = 0;
    //7天内平台申请数量
    private Integer applyPlateform7daysParserScore = 0;
    //7天内P2P平台申请数量
    private Integer applyP2pPlateform7daysParserScore = 0;
    //3个月内平台申请数量
    private Integer applyPlateform3MParserScore = 0;
    //3个月内P2P平台申请数量
    private Integer applyP2pPlateform3MParserScore = 0;

    //聚信立:是否实名:实名认证、非实名认证
    private Integer realNameParserScore = 0;

    //聚信立:手机使用时长
    private Integer phoneUsedTimeParserScore = 0;

    //聚信立:查询过该用户的企业数量
    private Integer searchedOrgCntParserScore = 0;
    //聚信立:电话号码注册过的相关企业(数量)
    private Double registerOrgCntParserScore = 0.0;

    //身份证关联的手机号码
    private Double idcardWithOtherPhonesParserScore = 0.0;

    //身份证号关联的其他姓名
    private Double idcardWithOtherNamesParserScore = 0.0;


    //手机号关联的身份证号码
    private Double phoneWithOtherIdcardsParserScore = 0.0;

    //手机号关联的其他姓名
    private Double phoneWithOtherNamesParserScore = 0.0;

    //黑中介分数
    private Double blackInfoPhoneGrayScoreParserScore = 0.0;

    //朋友圈所在地与工作地是否一致.一致/不一致/无数据
    private Integer workPlaceConsistentParserScore = 0;

    //第一联系人排名
    private Integer contact1MobileRankParserScore = 0;

    //第二联系人排名
    private Integer contact2MobileRankParserScore = 0;

    //贷款类被叫机构数
    private Integer loanOrgCallCntParserScore = 0;

    //贷款类联系机构数
    private Integer contactLoanOrgParserScore = 0;

    //贷款类被叫次数
    private Integer loanOrgCallCntTimesParserScore = 0;

    //银行类被叫机构数
    private Integer bankLoanOrgCallCntParserScore = 0;

    //银行类被叫次数
    private Integer bankLoanOrgCallCntTimesParserScore = 0;

    //信用卡类被叫机构数
    private Integer creditLoanOrgCallCntParserScore = 0;

    //信用卡类被叫次数
    private Integer creditLoanOrgCallCntTimesParserScore = 0;

    //律师类联系次数
    private Double lawyerContactedCntParserScore = 0.0;

    //110联系次数
    private Double contacted_110_CntParserScore = 0.0;

    //互通电话号码个数
    private Integer eachOtherContactsCntParserScore = 0;

    //近6个月静默天数
    private Integer silentDaysSumParserScore = 0;

    //近6个月出行次数
    private Double tripTimesParserScore = 0.0;

    //--------------------[分数]--------end--------------------------------------
    //======================= 汇总分数============ start = ==========================
    //反欺诈
    private Integer antiFraudScore = 0;

    //个人基本信息
    private Integer personalBasicInfoScore = 0;

    //工作信息
    private Integer workInfoScore = 0;

    //信用记录
    private Integer creditRecordScore = 0;

    //行为数据
    private Double behavioralDataScore = 0.0;
    //======================= 汇总分数============ end = ==========================
    //================= 增加部分= start ============================================

    //1个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications1Mths;

    //6个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications6Mths;

    //12个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications12Mths;

    //12个月之前申请数量（60个月内-12个月内）
    private Integer numberPlatformApplicationsOver12Mths;

    //性别
    private String sexUser;

    //户籍地
    private String placeRegister;

    //================= 增加部分= end ============================================

    //================= 增加部分 start 对应的分数 ===============================

    //1个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications1MthsParserScore;

    //6个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications6MthsParserScore;

    //12个月内申请人在多个平台申请借款
    private Integer numberPlatformApplications12MthsParserScore;

    //12个月之前申请数量（60个月内-12个月内）
    private Integer numberPlatformApplicationsOver12MthsParserScore;
    //性别
    private Integer sexUserParserScore;

    //户籍地
    private Integer placeRegisterParserScore;
    //============= 增加部分== end=====对应分数===========
    // 3个月内身份证关联多个申请信息-家庭地址
    private Integer numberBehaviorIdWithApplications3MthsHomeAddr;
    //3个月内身份证关联多个申请信息-关联邮箱
    private Integer numberBehaviorIdWithApplications3MthsEmail;
    //3个月内身份证关联多个申请信息-手机
    private Integer numberBehaviorIdWithApplications3MthsPhone;
    //3个月内申请信息关联多个身份证-家庭地址
    private Integer numberBehaviorApplicationsWithId3MthsHomeAddr;
    //3个月内申请信息关联多个身份证-关联邮箱
    private Integer numberBehaviorApplicationsWithId3MthsEmail;
    //3个月内申请信息关联多个身份证-手机
    private Integer numberBehaviorApplicationsWithId3MthsPhone;
    //7天内设备或身份证或手机号申请次数过多-设备
    private Integer numberBehaviorDeviceIdPhone7DaysDevice;
    //7天内设备或身份证或手机号申请次数过多-身份证
    private Integer numberBehaviorDeviceIdPhone7DaysId;
    //7天内设备或身份证或手机号申请次数过多-手机
    private Integer numberBehaviorDeviceIdPhone7DaysPhone;
    //1个月内设备或身份证或手机号申请次数过多-设备
    private Integer numberBehaviorDeviceIdPhone1MthsDevice;
    //1个月内设备或身份证或手机号申请次数过多-身份证
    private Integer numberBehaviorDeviceIdPhone1MthsId;
    //1个月内设备或身份证或手机号申请次数过多-手机
    private Integer numberBehaviorDeviceIdPhone1MthsPhone;
    //1天内身份证使用过多设备进行申请-1天
    private Integer numberBehaviorIdWithDevices1Days1D;
    //7天内身份证使用过多设备进行申请-1天
    private Integer numberBehaviorIdWithDevices7Days1D;
    //7天内身份证使用过多设备进行申请-7天
    private Integer numberBehaviorIdWithDevices7Days7D;
    //1个月内身份证使用过多设备进行申请-1天
    private Integer numberBehaviorIdWithDevices1Mths1D;
    //1个月内身份证使用过多设备进行申请-7天
    private Integer numberBehaviorIdWithDevices1Mths7D;
    //1个月内身份证使用过多设备进行申请-1个月
    private Integer numberBehaviorIdWithDevices1Mths1M;

    //芝麻行业关注名单结清状态
    private String zmSettlement;
    //聚信立_身份证号法院黑名单检查是否出现
    private Boolean jxlIdCardCourtBlacklistArised;
    //额度建议
    private String reportAmoutAdvice;

    //芝麻信用状态分数
    private Integer zmSettlementParserScore;

    //-------- 2016-11-25 -- 新增字段部分 -start--------------------------------------------
//	用户姓名与运营商提供的姓名匹配情况
    private String phoneCheckNameIsRight;

    //	用户身份证号与运营商提供的身份证号码匹配情况
    private String phoneCheckIdIsRight;

    //运营商身份匹配情况
    private Boolean phoneCheckCertification;

    //	全国地区通话时间占比
    private String regularCircleMobileTimePerc;
    //	号码被标记的名称 -投资、担保、贷款且被叫次数
    private Integer contactedInvestGuarLoan;

    //	号码被标记的名称 -店主、招聘且被叫次数
    private Integer contactedShopRecr;
    //	号码被标记的名称 -信用卡中心且被叫次数
    private Integer contactedCreditCardCenter;
    //	号码被标记的名称 -拍拍贷且被叫次数
    private Integer contactedPpdai;
    //	号码被标记的名称 -捷信且被叫次数
    private Integer contactedHomecredit;
    //	号码被标记的名称 -闪银且被叫次数
    private Integer contactedWecash;
    //	号码被标记的名称 -借贷宝且被叫次数
    private Integer contactedJiedaibao;
    //	号码被标记的名称 -小花钱包且被叫次数
    private Integer contactedXiaohua;
    //	号码被标记的名称 -佰仟金融且被叫次数
    private Integer contactedBillionsfinance;
    //	报告总通话次数
    private Integer callSumCnt;
    //	主叫或被叫次数为0
    private Boolean callSumCntIsZero;
    //	主叫占比
    private Double contactPerc;
    //	近一个月联系次数
    private Integer callLastMth;
    //	近一周联系次数
    private Integer callLastWeek;
    //	通话联系次数前5位都是短号
    private Boolean callTop5IsShort;
    //	通话记录联系次数均为1
    private Boolean contactCntIsOne;

    //	出行目的地命中澳门
    private Integer destinationIsMacao;
    //	手机号码月均消费金额
    private Double phoneConsumptionMthsAver;
    //	月通话时长
    private Double phoneContactMthsAver;
    //	出行时间
    private Integer tripTime;
    //  最新时间比
    Double lastContactTimeVsReportTime = 0d;

    //通话数据分析-号码标记被催收
    private Integer debtAskedContact;

    //通话数据分析-号码标记被催收描述
    private List<String> debtAskedContactDesc;

    //聚信立公积金：蜂贷自动化审核字段-公积金缴存城市
    private String fundCity;
    //聚信立淘宝原始数据
    private String taobaoReliability;
    //聚信立淘宝原始数据
    private String jingdongReliability;
    //鲸数公积金：蜂贷自动化审核字段-公积金实名认证
    private String fundReliability;
    //综合
    private String ebusinessReliability;

    //	蜂贷自动化审核字段-详单数据推算在网时长（pengyCorName）
    private String pengyCorName;

    public String getPengyCorName() {
        return pengyCorName;
    }

    public void setPengyCorName(String pengyCorName) {
        this.pengyCorName = pengyCorName;
    }

    public String getFundCity() {
        return fundCity;
    }

    public void setFundCity(String fundCity) {
        this.fundCity = fundCity;
    }

    public String getTaobaoReliability() {
        return taobaoReliability;
    }

    public void setTaobaoReliability(String taobaoReliability) {
        this.taobaoReliability = taobaoReliability;
    }

    public String getJingdongReliability() {
        return jingdongReliability;
    }

    public void setJingdongReliability(String jingdongReliability) {
        this.jingdongReliability = jingdongReliability;
    }

    public String getEbusinessReliability() {
        return ebusinessReliability;
    }

    public void setEbusinessReliability(String ebusinessReliability) {
        this.ebusinessReliability = ebusinessReliability;
    }

    public String getFundReliability() {
        return fundReliability;
    }

    public void setFundReliability(String fundReliability) {
        this.fundReliability = fundReliability;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getDebtAskedContact() {
        return debtAskedContact;
    }

    public void setDebtAskedContact(Integer debtAskedContact) {
        this.debtAskedContact = debtAskedContact;
    }

    public List<String> getDebtAskedContactDesc() {
        return debtAskedContactDesc;
    }

    public void setDebtAskedContactDesc(List<String> debtAskedContactDesc) {
        this.debtAskedContactDesc = debtAskedContactDesc;
    }

    public Double getLastContactTimeVsReportTime() {
        return lastContactTimeVsReportTime;
    }

    public void setLastContactTimeVsReportTime(Double lastContactTimeVsReportTime) {
        this.lastContactTimeVsReportTime = lastContactTimeVsReportTime;
    }

    //-------- 2016-11-25 -- 新增字段部分 -end--------------------------------------------
    public String getPhoneCheckNameIsRight() {
        return phoneCheckNameIsRight;
    }

    public void setPhoneCheckNameIsRight(String phoneCheckNameIsRight) {
        this.phoneCheckNameIsRight = phoneCheckNameIsRight;
    }


    public Boolean getPhoneCheckCertification() {
        return phoneCheckCertification;
    }

    public void setPhoneCheckCertification(Boolean phoneCheckCertification) {
        this.phoneCheckCertification = phoneCheckCertification;
    }


    public String getPhoneCheckIdIsRight() {
        return phoneCheckIdIsRight;
    }

    public void setPhoneCheckIdIsRight(String phoneCheckIdIsRight) {
        this.phoneCheckIdIsRight = phoneCheckIdIsRight;
    }

    public String getRegularCircleMobileTimePerc() {
        return regularCircleMobileTimePerc;
    }

    public void setRegularCircleMobileTimePerc(String regularCircleMobileTimePerc) {
        this.regularCircleMobileTimePerc = regularCircleMobileTimePerc;
    }

    public Integer getContactedInvestGuarLoan() {
        return contactedInvestGuarLoan;
    }

    public void setContactedInvestGuarLoan(Integer contactedInvestGuarLoan) {
        this.contactedInvestGuarLoan = contactedInvestGuarLoan;
    }

    public Integer getContactedShopRecr() {
        return contactedShopRecr;
    }

    public void setContactedShopRecr(Integer contactedShopRecr) {
        this.contactedShopRecr = contactedShopRecr;
    }

    public Integer getContactedCreditCardCenter() {
        return contactedCreditCardCenter;
    }

    public void setContactedCreditCardCenter(Integer contactedCreditCardCenter) {
        this.contactedCreditCardCenter = contactedCreditCardCenter;
    }

    public Integer getContactedPpdai() {
        return contactedPpdai;
    }

    public void setContactedPpdai(Integer contactedPpdai) {
        this.contactedPpdai = contactedPpdai;
    }

    public Integer getContactedHomecredit() {
        return contactedHomecredit;
    }

    public void setContactedHomecredit(Integer contactedHomecredit) {
        this.contactedHomecredit = contactedHomecredit;
    }

    public Integer getContactedWecash() {
        return contactedWecash;
    }

    public void setContactedWecash(Integer contactedWecash) {
        this.contactedWecash = contactedWecash;
    }

    public Integer getContactedJiedaibao() {
        return contactedJiedaibao;
    }

    public void setContactedJiedaibao(Integer contactedJiedaibao) {
        this.contactedJiedaibao = contactedJiedaibao;
    }

    public Integer getContactedXiaohua() {
        return contactedXiaohua;
    }

    public void setContactedXiaohua(Integer contactedXiaohua) {
        this.contactedXiaohua = contactedXiaohua;
    }

    public Integer getContactedBillionsfinance() {
        return contactedBillionsfinance;
    }

    public void setContactedBillionsfinance(Integer contactedBillionsfinance) {
        this.contactedBillionsfinance = contactedBillionsfinance;
    }

    public Integer getCallSumCnt() {
        return callSumCnt;
    }

    public void setCallSumCnt(Integer callSumCnt) {
        this.callSumCnt = callSumCnt;
    }

    public Boolean getCallSumCntIsZero() {
        return callSumCntIsZero;
    }

    public void setCallSumCntIsZero(Boolean callSumCntIsZero) {
        this.callSumCntIsZero = callSumCntIsZero;
    }

    public Double getContactPerc() {
        return contactPerc;
    }

    public void setContactPerc(Double contactPerc) {
        this.contactPerc = contactPerc;
    }

    public Integer getCallLastMth() {
        return callLastMth;
    }

    public void setCallLastMth(Integer callLastMth) {
        this.callLastMth = callLastMth;
    }

    public Integer getCallLastWeek() {
        return callLastWeek;
    }

    public void setCallLastWeek(Integer callLastWeek) {
        this.callLastWeek = callLastWeek;
    }

    public Boolean getCallTop5IsShort() {
        return callTop5IsShort;
    }

    public void setCallTop5IsShort(Boolean callTop5IsShort) {
        this.callTop5IsShort = callTop5IsShort;
    }

    public Boolean getContactCntIsOne() {
        return contactCntIsOne;
    }

    public void setContactCntIsOne(Boolean contactCntIsOne) {
        this.contactCntIsOne = contactCntIsOne;
    }

    public Integer getDestinationIsMacao() {
        return destinationIsMacao;
    }

    public void setDestinationIsMacao(Integer destinationIsMacao) {
        this.destinationIsMacao = destinationIsMacao;
    }

    public Double getPhoneConsumptionMthsAver() {
        return phoneConsumptionMthsAver;
    }

    public void setPhoneConsumptionMthsAver(Double phoneConsumptionMthsAver) {
        this.phoneConsumptionMthsAver = phoneConsumptionMthsAver;
    }

    public Double getPhoneContactMthsAver() {
        return phoneContactMthsAver;
    }

    public void setPhoneContactMthsAver(Double phoneContactMthsAver) {
        this.phoneContactMthsAver = phoneContactMthsAver;
    }

    public Integer getTripTime() {
        return tripTime;
    }

    public void setTripTime(Integer tripTime) {
        this.tripTime = tripTime;
    }

    public Integer getZmSettlementParserScore() {
        return zmSettlementParserScore;
    }

    public void setZmSettlementParserScore(Integer zmSettlementParserScore) {
        this.zmSettlementParserScore = zmSettlementParserScore;
    }

    public String getZmSettlement() {
        return zmSettlement;
    }

    public void setZmSettlement(String zmSettlement) {
        this.zmSettlement = zmSettlement;
    }

    public String getReportAmoutAdvice() {
        return reportAmoutAdvice;
    }

    public void setReportAmoutAdvice(String reportAmoutAdvice) {
        this.reportAmoutAdvice = reportAmoutAdvice;
    }

    public Integer getNumberBehaviorIdWithApplications3MthsHomeAddr() {
        return numberBehaviorIdWithApplications3MthsHomeAddr;
    }

    public void setNumberBehaviorIdWithApplications3MthsHomeAddr(Integer numberBehaviorIdWithApplications3MthsHomeAddr) {
        this.numberBehaviorIdWithApplications3MthsHomeAddr = numberBehaviorIdWithApplications3MthsHomeAddr;
    }

    public Integer getNumberBehaviorIdWithApplications3MthsEmail() {
        return numberBehaviorIdWithApplications3MthsEmail;
    }

    public void setNumberBehaviorIdWithApplications3MthsEmail(Integer numberBehaviorIdWithApplications3MthsEmail) {
        this.numberBehaviorIdWithApplications3MthsEmail = numberBehaviorIdWithApplications3MthsEmail;
    }

    public Integer getNumberBehaviorIdWithApplications3MthsPhone() {
        return numberBehaviorIdWithApplications3MthsPhone;
    }

    public void setNumberBehaviorIdWithApplications3MthsPhone(Integer numberBehaviorIdWithApplications3MthsPhone) {
        this.numberBehaviorIdWithApplications3MthsPhone = numberBehaviorIdWithApplications3MthsPhone;
    }

    public Integer getNumberBehaviorApplicationsWithId3MthsHomeAddr() {
        return numberBehaviorApplicationsWithId3MthsHomeAddr;
    }

    public void setNumberBehaviorApplicationsWithId3MthsHomeAddr(Integer numberBehaviorApplicationsWithId3MthsHomeAddr) {
        this.numberBehaviorApplicationsWithId3MthsHomeAddr = numberBehaviorApplicationsWithId3MthsHomeAddr;
    }

    public Integer getNumberBehaviorApplicationsWithId3MthsEmail() {
        return numberBehaviorApplicationsWithId3MthsEmail;
    }

    public void setNumberBehaviorApplicationsWithId3MthsEmail(Integer numberBehaviorApplicationsWithId3MthsEmail) {
        this.numberBehaviorApplicationsWithId3MthsEmail = numberBehaviorApplicationsWithId3MthsEmail;
    }

    public Integer getNumberBehaviorApplicationsWithId3MthsPhone() {
        return numberBehaviorApplicationsWithId3MthsPhone;
    }

    public void setNumberBehaviorApplicationsWithId3MthsPhone(Integer numberBehaviorApplicationsWithId3MthsPhone) {
        this.numberBehaviorApplicationsWithId3MthsPhone = numberBehaviorApplicationsWithId3MthsPhone;
    }

    public Integer getNumberBehaviorDeviceIdPhone7DaysDevice() {
        return numberBehaviorDeviceIdPhone7DaysDevice;
    }

    public void setNumberBehaviorDeviceIdPhone7DaysDevice(Integer numberBehaviorDeviceIdPhone7DaysDevice) {
        this.numberBehaviorDeviceIdPhone7DaysDevice = numberBehaviorDeviceIdPhone7DaysDevice;
    }

    public Integer getNumberBehaviorDeviceIdPhone7DaysId() {
        return numberBehaviorDeviceIdPhone7DaysId;
    }

    public void setNumberBehaviorDeviceIdPhone7DaysId(Integer numberBehaviorDeviceIdPhone7DaysId) {
        this.numberBehaviorDeviceIdPhone7DaysId = numberBehaviorDeviceIdPhone7DaysId;
    }

    public Integer getNumberBehaviorDeviceIdPhone7DaysPhone() {
        return numberBehaviorDeviceIdPhone7DaysPhone;
    }

    public void setNumberBehaviorDeviceIdPhone7DaysPhone(Integer numberBehaviorDeviceIdPhone7DaysPhone) {
        this.numberBehaviorDeviceIdPhone7DaysPhone = numberBehaviorDeviceIdPhone7DaysPhone;
    }

    public Integer getNumberBehaviorDeviceIdPhone1MthsDevice() {
        return numberBehaviorDeviceIdPhone1MthsDevice;
    }

    public void setNumberBehaviorDeviceIdPhone1MthsDevice(Integer numberBehaviorDeviceIdPhone1MthsDevice) {
        this.numberBehaviorDeviceIdPhone1MthsDevice = numberBehaviorDeviceIdPhone1MthsDevice;
    }

    public Integer getNumberBehaviorDeviceIdPhone1MthsId() {
        return numberBehaviorDeviceIdPhone1MthsId;
    }

    public void setNumberBehaviorDeviceIdPhone1MthsId(Integer numberBehaviorDeviceIdPhone1MthsId) {
        this.numberBehaviorDeviceIdPhone1MthsId = numberBehaviorDeviceIdPhone1MthsId;
    }

    public Integer getNumberBehaviorDeviceIdPhone1MthsPhone() {
        return numberBehaviorDeviceIdPhone1MthsPhone;
    }

    public void setNumberBehaviorDeviceIdPhone1MthsPhone(Integer numberBehaviorDeviceIdPhone1MthsPhone) {
        this.numberBehaviorDeviceIdPhone1MthsPhone = numberBehaviorDeviceIdPhone1MthsPhone;
    }

    public Integer getNumberBehaviorIdWithDevices1Days1D() {
        return numberBehaviorIdWithDevices1Days1D;
    }

    public void setNumberBehaviorIdWithDevices1Days1D(Integer numberBehaviorIdWithDevices1Days1D) {
        this.numberBehaviorIdWithDevices1Days1D = numberBehaviorIdWithDevices1Days1D;
    }

    public Integer getNumberBehaviorIdWithDevices7Days1D() {
        return numberBehaviorIdWithDevices7Days1D;
    }

    public void setNumberBehaviorIdWithDevices7Days1D(Integer numberBehaviorIdWithDevices7Days1D) {
        this.numberBehaviorIdWithDevices7Days1D = numberBehaviorIdWithDevices7Days1D;
    }

    public Integer getNumberBehaviorIdWithDevices7Days7D() {
        return numberBehaviorIdWithDevices7Days7D;
    }

    public void setNumberBehaviorIdWithDevices7Days7D(Integer numberBehaviorIdWithDevices7Days7D) {
        this.numberBehaviorIdWithDevices7Days7D = numberBehaviorIdWithDevices7Days7D;
    }

    public Integer getNumberBehaviorIdWithDevices1Mths1D() {
        return numberBehaviorIdWithDevices1Mths1D;
    }

    public void setNumberBehaviorIdWithDevices1Mths1D(Integer numberBehaviorIdWithDevices1Mths1D) {
        this.numberBehaviorIdWithDevices1Mths1D = numberBehaviorIdWithDevices1Mths1D;
    }

    public Integer getNumberBehaviorIdWithDevices1Mths7D() {
        return numberBehaviorIdWithDevices1Mths7D;
    }

    public void setNumberBehaviorIdWithDevices1Mths7D(Integer numberBehaviorIdWithDevices1Mths7D) {
        this.numberBehaviorIdWithDevices1Mths7D = numberBehaviorIdWithDevices1Mths7D;
    }

    public Integer getNumberBehaviorIdWithDevices1Mths1M() {
        return numberBehaviorIdWithDevices1Mths1M;
    }

    public void setNumberBehaviorIdWithDevices1Mths1M(Integer numberBehaviorIdWithDevices1Mths1M) {
        this.numberBehaviorIdWithDevices1Mths1M = numberBehaviorIdWithDevices1Mths1M;
    }

    public Integer getPlaceRegisterParserScore() {
        return placeRegisterParserScore;
    }

    public void setPlaceRegisterParserScore(Integer placeRegisterParserScore) {
        this.placeRegisterParserScore = placeRegisterParserScore;
    }

    public Integer getNumberPlatformApplications1Mths() {
        return numberPlatformApplications1Mths;
    }

    public void setNumberPlatformApplications1Mths(Integer numberPlatformApplications1Mths) {
        this.numberPlatformApplications1Mths = numberPlatformApplications1Mths;
    }

    public Integer getNumberPlatformApplications6Mths() {
        return numberPlatformApplications6Mths;
    }

    public void setNumberPlatformApplications6Mths(Integer numberPlatformApplications6Mths) {
        this.numberPlatformApplications6Mths = numberPlatformApplications6Mths;
    }

    public Integer getNumberPlatformApplications12Mths() {
        return numberPlatformApplications12Mths;
    }

    public void setNumberPlatformApplications12Mths(Integer numberPlatformApplications12Mths) {
        this.numberPlatformApplications12Mths = numberPlatformApplications12Mths;
    }

    public Integer getNumberPlatformApplicationsOver12Mths() {
        return numberPlatformApplicationsOver12Mths;
    }

    public void setNumberPlatformApplicationsOver12Mths(Integer numberPlatformApplicationsOver12Mths) {
        this.numberPlatformApplicationsOver12Mths = numberPlatformApplicationsOver12Mths;
    }

    public Boolean getJxlIdCardCourtBlacklistArised() {
        return jxlIdCardCourtBlacklistArised;
    }

    public void setJxlIdCardCourtBlacklistArised(Boolean jxlIdCardCourtBlacklistArised) {
        this.jxlIdCardCourtBlacklistArised = jxlIdCardCourtBlacklistArised;
    }

    public String getSexUser() {
        return sexUser;
    }

    public void setSexUser(String sexUser) {
        this.sexUser = sexUser;
    }

    public Integer getNumberPlatformApplications1MthsParserScore() {
        return numberPlatformApplications1MthsParserScore;
    }

    public void setNumberPlatformApplications1MthsParserScore(Integer numberPlatformApplications1MthsParserScore) {
        this.numberPlatformApplications1MthsParserScore = numberPlatformApplications1MthsParserScore;
    }

    public Integer getNumberPlatformApplications6MthsParserScore() {
        return numberPlatformApplications6MthsParserScore;
    }

    public void setNumberPlatformApplications6MthsParserScore(Integer numberPlatformApplications6MthsParserScore) {
        this.numberPlatformApplications6MthsParserScore = numberPlatformApplications6MthsParserScore;
    }

    public Integer getNumberPlatformApplications12MthsParserScore() {
        return numberPlatformApplications12MthsParserScore;
    }

    public void setNumberPlatformApplications12MthsParserScore(Integer numberPlatformApplications12MthsParserScore) {
        this.numberPlatformApplications12MthsParserScore = numberPlatformApplications12MthsParserScore;
    }

    public Integer getNumberPlatformApplicationsOver12MthsParserScore() {
        return numberPlatformApplicationsOver12MthsParserScore;
    }

    public void setNumberPlatformApplicationsOver12MthsParserScore(Integer numberPlatformApplicationsOver12MthsParserScore) {
        this.numberPlatformApplicationsOver12MthsParserScore = numberPlatformApplicationsOver12MthsParserScore;
    }

    public Integer getSexUserParserScore() {
        return sexUserParserScore;
    }

    public void setSexUserParserScore(Integer sexUserParserScore) {
        this.sexUserParserScore = sexUserParserScore;
    }

    public Integer getAntiFraudScore() {
        return antiFraudScore;
    }

    public void setAntiFraudScore(Integer antiFraudScore) {
        this.antiFraudScore = antiFraudScore;
    }

    public void addAntiFraudScore(Integer score) {
        logger.info("反欺诈得分.antiFraudScore={},score={}", antiFraudScore, score);
        this.antiFraudScore = this.antiFraudScore + score;
    }

    public Integer getPersonalBasicInfoScore() {
        return personalBasicInfoScore;
    }

    public void setPersonalBasicInfoScore(Integer personalBasicInfoScore) {
        this.personalBasicInfoScore = personalBasicInfoScore;
    }

    public void addPersonalBasicInfoScore(Integer score) {
        logger.info("个人基本信息得分.personalBasicInfoScore={},score={}", personalBasicInfoScore, score);
        this.personalBasicInfoScore = this.personalBasicInfoScore + score;
    }

    public Integer getWorkInfoScore() {
        return workInfoScore;
    }

    public void setWorkInfoScore(Integer workInfoScore) {
        this.workInfoScore = workInfoScore;
    }

    public void addWorkInfoScore(Integer score) {
        logger.info("工作分数累加：workInfoScore={},score={}", workInfoScore, score);
        this.workInfoScore = this.workInfoScore + score;
    }

    public Integer getCreditRecordScore() {
        return creditRecordScore;
    }

    public void setCreditRecordScore(Integer creditRecordScore) {
        this.creditRecordScore = creditRecordScore;
    }

    private static final Logger logger = LoggerFactory.getLogger(CreditScore.class);

    public void addCreditRecordScore(Integer score) {
        logger.info("信用分数累加：creditRecordScore={},score={}", creditRecordScore, score);
        this.creditRecordScore = this.creditRecordScore + score;
    }

    public Double getBehavioralDataScore() {
        return behavioralDataScore;
    }

    public void setBehavioralDataScore(Double behavioralDataScore) {
        this.behavioralDataScore = behavioralDataScore;
    }

    public void addBehavioralDataScore(Object obj) {
        logger.info("行为数据得分：behavioralDataScore={},score={}", behavioralDataScore, Double.parseDouble(String.valueOf(obj)));
        this.behavioralDataScore = this.behavioralDataScore + Double.parseDouble(String.valueOf(obj));
    }


    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public boolean isFinancyBlack() {
        return financyBlack;
    }

    public void setFinancyBlack(boolean financyBlack) {
        this.financyBlack = financyBlack;
    }

    public Double getFundDeposit() {
        return fundDeposit;
    }

    public void setFundDeposit(Double fundDeposit) {
        this.fundDeposit = fundDeposit;
    }

    public Double getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(Double fundBalance) {
        this.fundBalance = fundBalance;
    }

    public Integer getFundNum() {
        return fundNum;
    }

    public void setFundNum(Integer fundNum) {
        this.fundNum = fundNum;
    }

    public String isSameWorkPlace() {
        return isSameWorkPlace;
    }

    public void setSameWorkPlace(String isSameWorkPlace) {
        this.isSameWorkPlace = isSameWorkPlace;
    }

    public Integer getFundExtract() {
        return fundExtract;
    }

    public void setFundExtract(Integer fundExtract) {
        this.fundExtract = fundExtract;
    }

    public Integer getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Integer loanCount) {
        this.loanCount = loanCount;
    }

    public Double getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(Double loanAmt) {
        this.loanAmt = loanAmt;
    }

    public Double getLoanRatio() {
        return loanRatio;
    }

    public void setLoanRatio(Double loanRatio) {
        this.loanRatio = loanRatio;
    }

    public Double getMaxCreditAmt() {
        return maxCreditAmt;
    }

    public void setMaxCreditAmt(Double maxCreditAmt) {
        this.maxCreditAmt = maxCreditAmt;
    }

    public Integer getFirstLoanTime() {
        return firstLoanTime;
    }

    public void setFirstLoanTime(Integer firstLoanTime) {
        this.firstLoanTime = firstLoanTime;
    }

    public Integer getLoanReportQueryRecord() {
        return loanReportQueryRecord;
    }

    public void setLoanReportQueryRecord(Integer loanReportQueryRecord) {
        this.loanReportQueryRecord = loanReportQueryRecord;
    }

    public Integer getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(Integer paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public Integer getCurrentCreditAmt() {
        return currentCreditAmt;
    }

    public void setCurrentCreditAmt(Integer currentCreditAmt) {
        this.currentCreditAmt = currentCreditAmt;
    }

    public Integer getEaliestCreditTime() {
        return ealiestCreditTime;
    }

    public void setEaliestCreditTime(Integer ealiestCreditTime) {
        this.ealiestCreditTime = ealiestCreditTime;
    }

    public Integer getZcEaliestCreditTime() {
        return zcEaliestCreditTime;
    }

    public void setZcEaliestCreditTime(Integer zcEaliestCreditTime) {
        this.zcEaliestCreditTime = zcEaliestCreditTime;
    }

    public Double getZcScoreRate() {
        return zcScoreRate;
    }

    public void setZcScoreRate(Double zcScoreRate) {
        this.zcScoreRate = zcScoreRate;
    }

    public boolean isShamData() {
        return shamData;
    }

    public void setShamData(boolean shamData) {
        this.shamData = shamData;
    }

    public Integer getZcPayBackRecord() {
        return zcPayBackRecord;
    }

    public void setZcPayBackRecord(Integer zcPayBackRecord) {
        this.zcPayBackRecord = zcPayBackRecord;
    }

    public Integer getStasticsTimes() {
        return stasticsTimes;
    }

    public void setStasticsTimes(Integer stasticsTimes) {
        this.stasticsTimes = stasticsTimes;
    }

    public Integer getZmScore() {
        return zmScore;
    }

    public void setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
    }

    public Integer getApplyPlateform7days() {
        return applyPlateform7days;
    }

    public void setApplyPlateform7days(Integer applyPlateform7days) {
        this.applyPlateform7days = applyPlateform7days;
    }

    public Integer getApplyP2pPlateform7days() {
        return applyP2pPlateform7days;
    }

    public void setApplyP2pPlateform7days(Integer applyP2pPlateform7days) {
        this.applyP2pPlateform7days = applyP2pPlateform7days;
    }

    public Integer getApplyPlateform3M() {
        return applyPlateform3M;
    }

    public void setApplyPlateform3M(Integer applyPlateform3M) {
        this.applyPlateform3M = applyPlateform3M;
    }

    public Integer getApplyP2pPlateform3M() {
        return applyP2pPlateform3M;
    }

    public void setApplyP2pPlateform3M(Integer applyP2pPlateform3M) {
        this.applyP2pPlateform3M = applyP2pPlateform3M;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPhoneUsedTime() {
        return phoneUsedTime;
    }

    public void setPhoneUsedTime(Integer phoneUsedTime) {
        this.phoneUsedTime = phoneUsedTime;
    }

    public Integer getSearchedOrgCnt() {
        return searchedOrgCnt;
    }

    public void setSearchedOrgCnt(Integer searchedOrgCnt) {
        this.searchedOrgCnt = searchedOrgCnt;
    }

    public Integer getRegisterOrgCnt() {
        return registerOrgCnt;
    }

    public void setRegisterOrgCnt(Integer registerOrgCnt) {
        this.registerOrgCnt = registerOrgCnt;
    }

    public Integer getIdcardWithOtherPhones() {
        return idcardWithOtherPhones;
    }

    public void setIdcardWithOtherPhones(Integer idcardWithOtherPhones) {
        this.idcardWithOtherPhones = idcardWithOtherPhones;
    }

    public Integer getIdcardWithOtherNames() {
        return idcardWithOtherNames;
    }

    public void setIdcardWithOtherNames(Integer idcardWithOtherNames) {
        this.idcardWithOtherNames = idcardWithOtherNames;
    }

    public Integer getPhoneWithOtherIdcards() {
        return phoneWithOtherIdcards;
    }

    public void setPhoneWithOtherIdcards(Integer phoneWithOtherIdcards) {
        this.phoneWithOtherIdcards = phoneWithOtherIdcards;
    }

    public Integer getPhoneWithOtherNames() {
        return phoneWithOtherNames;
    }

    public void setPhoneWithOtherNames(Integer phoneWithOtherNames) {
        this.phoneWithOtherNames = phoneWithOtherNames;
    }

    public Integer getBlackInfoPhoneGrayScore() {
        return blackInfoPhoneGrayScore;
    }

    public void setBlackInfoPhoneGrayScore(Integer blackInfoPhoneGrayScore) {
        this.blackInfoPhoneGrayScore = blackInfoPhoneGrayScore;
    }

    public String getWorkPlaceConsistent() {
        return workPlaceConsistent;
    }

    public void setWorkPlaceConsistent(String workPlaceConsistent) {
        this.workPlaceConsistent = workPlaceConsistent;
    }

    public Integer getContact1MobileRank() {
        return contact1MobileRank;
    }

    public void setContact1MobileRank(Integer contact1MobileRank) {
        this.contact1MobileRank = contact1MobileRank;
    }

    public Integer getContact2MobileRank() {
        return contact2MobileRank;
    }

    public void setContact2MobileRank(Integer contact2MobileRank) {
        this.contact2MobileRank = contact2MobileRank;
    }

    public Integer getLoanOrgCallCnt() {
        return loanOrgCallCnt;
    }

    public void setLoanOrgCallCnt(Integer loanOrgCallCnt) {
        this.loanOrgCallCnt = loanOrgCallCnt;
    }

    public Integer getContactLoanOrg() {
        return contactLoanOrg;
    }

    public void setContactLoanOrg(Integer contactLoanOrg) {
        this.contactLoanOrg = contactLoanOrg;
    }

    public Integer getLoanOrgCallCntTimes() {
        return loanOrgCallCntTimes;
    }

    public void setLoanOrgCallCntTimes(Integer loanOrgCallCntTimes) {
        this.loanOrgCallCntTimes = loanOrgCallCntTimes;
    }

    public Integer getBankLoanOrgCallCnt() {
        return bankLoanOrgCallCnt;
    }

    public void setBankLoanOrgCallCnt(Integer bankLoanOrgCallCnt) {
        this.bankLoanOrgCallCnt = bankLoanOrgCallCnt;
    }

    public Integer getBankLoanOrgCallCntTimes() {
        return bankLoanOrgCallCntTimes;
    }

    public void setBankLoanOrgCallCntTimes(Integer bankLoanOrgCallCntTimes) {
        this.bankLoanOrgCallCntTimes = bankLoanOrgCallCntTimes;
    }

    public Integer getCreditLoanOrgCallCnt() {
        return creditLoanOrgCallCnt;
    }

    public void setCreditLoanOrgCallCnt(Integer creditLoanOrgCallCnt) {
        this.creditLoanOrgCallCnt = creditLoanOrgCallCnt;
    }

    public Integer getCreditLoanOrgCallCntTimes() {
        return creditLoanOrgCallCntTimes;
    }

    public void setCreditLoanOrgCallCntTimes(Integer creditLoanOrgCallCntTimes) {
        this.creditLoanOrgCallCntTimes = creditLoanOrgCallCntTimes;
    }

    public Integer getLawyerContactedCnt() {
        return lawyerContactedCnt;
    }

    public void setLawyerContactedCnt(Integer lawyerContactedCnt) {
        this.lawyerContactedCnt = lawyerContactedCnt;
    }

    public Integer getContacted_110_Cnt() {
        return contacted_110_Cnt;
    }

    public void setContacted_110_Cnt(Integer contacted_110_Cnt) {
        this.contacted_110_Cnt = contacted_110_Cnt;
    }

    public Integer getEachOtherContactsCnt() {
        return eachOtherContactsCnt;
    }

    public void setEachOtherContactsCnt(Integer eachOtherContactsCnt) {
        this.eachOtherContactsCnt = eachOtherContactsCnt;
    }

    public Integer getTripTimes() {
        return tripTimes;
    }

    public void setTripTimes(Integer tripTimes) {
        this.tripTimes = tripTimes;
    }

    public Integer getSilentDaysSum() {
        return silentDaysSum;
    }

    public void setSilentDaysSum(Integer silentDaysSum) {
        this.silentDaysSum = silentDaysSum;
    }

    public Integer getAgeUser() {
        return ageUser;
    }

    public Integer isBlackParserScore() {
        return isBlackParserScore;
    }

    public void setBlackParserScore(Integer isBlackParserScore) {
        this.isBlackParserScore = isBlackParserScore;
    }

    public Integer getRiskLevelParserScore() {
        return riskLevelParserScore;
    }

    public void setRiskLevelParserScore(Integer riskLevelParserScore) {
        this.riskLevelParserScore = riskLevelParserScore;
    }

    public Integer isFinancyBlackParserScore() {
        return isFinancyBlackParserScore;
    }

    public void setFinancyBlackParserScore(Integer isFinancyBlackParserScore) {
        this.isFinancyBlackParserScore = isFinancyBlackParserScore;
    }

    public void setAgeUser(Integer ageUser) {
        this.ageUser = ageUser;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getDegreeDiploma() {
        return degreeDiploma;
    }

    public void setDegreeDiploma(String degreeDiploma) {
        this.degreeDiploma = degreeDiploma;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public String getDepartmentCompany() {
        return departmentCompany;
    }

    public void setDepartmentCompany(String departmentCompany) {
        this.departmentCompany = departmentCompany;
    }

    public String getIsSameWorkPlace() {
        return isSameWorkPlace;
    }

    public void setIsSameWorkPlace(String isSameWorkPlace) {
        this.isSameWorkPlace = isSameWorkPlace;
    }

    public Integer getAgeUserParserScore() {
        return ageUserParserScore;
    }

    public void setAgeUserParserScore(Integer ageUserParserScore) {
        this.ageUserParserScore = ageUserParserScore;
    }

    public Integer getMarriageStatusParserScore() {
        return marriageStatusParserScore;
    }

    public void setMarriageStatusParserScore(Integer marriageStatusParserScore) {
        this.marriageStatusParserScore = marriageStatusParserScore;
    }

    public Integer getDegreeDiplomaParserScore() {
        return degreeDiplomaParserScore;
    }

    public void setDegreeDiplomaParserScore(Integer degreeDiplomaParserScore) {
        this.degreeDiplomaParserScore = degreeDiplomaParserScore;
    }

    public Integer getPropertyCompanyParserScore() {
        return propertyCompanyParserScore;
    }

    public void setPropertyCompanyParserScore(Integer propertyCompanyParserScore) {
        this.propertyCompanyParserScore = propertyCompanyParserScore;
    }

    public Integer getDepartmentCompanyParserScore() {
        return departmentCompanyParserScore;
    }

    public void setDepartmentCompanyParserScore(Integer departmentCompanyParserScore) {
        this.departmentCompanyParserScore = departmentCompanyParserScore;
    }

    public Double getReportScore() {
        return reportScore;
    }

    public void setReportScore(Double reportScore) {
        this.reportScore = reportScore;
    }

    public void addReportScore(Object score) {
        logger.info("总分数:综合得分.reportScore={},score={}", reportScore, score);
        this.reportScore = this.reportScore + Double.parseDouble(String.valueOf(score));
    }

    public Integer getIsBlackParserScore() {
        return isBlackParserScore;
    }

    public void setIsBlackParserScore(Integer isBlackParserScore) {
        this.isBlackParserScore = isBlackParserScore;
    }

    public Integer getIsFinancyBlackParserScore() {
        return isFinancyBlackParserScore;
    }

    public void setIsFinancyBlackParserScore(Integer isFinancyBlackParserScore) {
        this.isFinancyBlackParserScore = isFinancyBlackParserScore;
    }

    public Integer getFundDepositParserScore() {
        return fundDepositParserScore;
    }

    public void setFundDepositParserScore(Integer fundDepositParserScore) {
        this.fundDepositParserScore = fundDepositParserScore;
    }

    public Integer getFundBalanceParserScore() {
        return fundBalanceParserScore;
    }

    public void setFundBalanceParserScore(Integer fundBalanceParserScore) {
        this.fundBalanceParserScore = fundBalanceParserScore;
    }

    public Integer getFundNumParserScore() {
        return fundNumParserScore;
    }

    public void setFundNumParserScore(Integer fundNumParserScore) {
        this.fundNumParserScore = fundNumParserScore;
    }

    public Integer getIsSameWorkPlaceParserScore() {
        return isSameWorkPlaceParserScore;
    }

    public void setIsSameWorkPlaceParserScore(Integer isSameWorkPlaceParserScore) {
        this.isSameWorkPlaceParserScore = isSameWorkPlaceParserScore;
    }

    public Integer getFundExtractParserScore() {
        return fundExtractParserScore;
    }

    public void setFundExtractParserScore(Integer fundExtractParserScore) {
        this.fundExtractParserScore = fundExtractParserScore;
    }

    public Integer getLoanCountParserScore() {
        return loanCountParserScore;
    }

    public void setLoanCountParserScore(Integer loanCountParserScore) {
        this.loanCountParserScore = loanCountParserScore;
    }

    public Integer getLoanAmtParserScore() {
        return loanAmtParserScore;
    }

    public void setLoanAmtParserScore(Integer loanAmtParserScore) {
        this.loanAmtParserScore = loanAmtParserScore;
    }

    public Integer getLoanRatioParserScore() {
        return loanRatioParserScore;
    }

    public void setLoanRatioParserScore(Integer loanRatioParserScore) {
        this.loanRatioParserScore = loanRatioParserScore;
    }

    public Integer getMaxCreditAmtParserScore() {
        return maxCreditAmtParserScore;
    }

    public void setMaxCreditAmtParserScore(Integer maxCreditAmtParserScore) {
        this.maxCreditAmtParserScore = maxCreditAmtParserScore;
    }

    public Integer getFirstLoanTimeParserScore() {
        return firstLoanTimeParserScore;
    }

    public void setFirstLoanTimeParserScore(Integer firstLoanTimeParserScore) {
        this.firstLoanTimeParserScore = firstLoanTimeParserScore;
    }

    public Integer getPaymentRecordParserScore() {
        return paymentRecordParserScore;
    }

    public void setPaymentRecordParserScore(Integer paymentRecordParserScore) {
        this.paymentRecordParserScore = paymentRecordParserScore;
    }

    public Integer getLoanReportQueryRecordParserScore() {
        return loanReportQueryRecordParserScore;
    }

    public void setLoanReportQueryRecordParserScore(Integer loanReportQueryRecordParserScore) {
        this.loanReportQueryRecordParserScore = loanReportQueryRecordParserScore;
    }

    public Integer getCurrentCreditAmtParserScore() {
        return currentCreditAmtParserScore;
    }

    public void setCurrentCreditAmtParserScore(Integer currentCreditAmtParserScore) {
        this.currentCreditAmtParserScore = currentCreditAmtParserScore;
    }

    public Integer getEaliestCreditTimeParserScore() {
        return ealiestCreditTimeParserScore;
    }

    public void setEaliestCreditTimeParserScore(Integer ealiestCreditTimeParserScore) {
        this.ealiestCreditTimeParserScore = ealiestCreditTimeParserScore;
    }

    public Integer getZcScoreRateParserScore() {
        return zcScoreRateParserScore;
    }

    public void setZcScoreRateParserScore(Integer zcScoreRateParserScore) {
        this.zcScoreRateParserScore = zcScoreRateParserScore;
    }

    public Integer getIsShamDataParserScore() {
        return isShamDataParserScore;
    }

    public void setIsShamDataParserScore(Integer isShamDataParserScore) {
        this.isShamDataParserScore = isShamDataParserScore;
    }

    public Integer getZcPayBackRecordParserScore() {
        return zcPayBackRecordParserScore;
    }

    public void setZcPayBackRecordParserScore(Integer zcPayBackRecordParserScore) {
        this.zcPayBackRecordParserScore = zcPayBackRecordParserScore;
    }

    public Integer getStasticsTimesParserScore() {
        return stasticsTimesParserScore;
    }

    public void setStasticsTimesParserScore(Integer stasticsTimesParserScore) {
        this.stasticsTimesParserScore = stasticsTimesParserScore;
    }

    public Integer getZmScoreParserScore() {
        return zmScoreParserScore;
    }

    public void setZmScoreParserScore(Integer zmScoreParserScore) {
        this.zmScoreParserScore = zmScoreParserScore;
    }

    public Integer getApplyPlateform7daysParserScore() {
        return applyPlateform7daysParserScore;
    }

    public void setApplyPlateform7daysParserScore(Integer applyPlateform7daysParserScore) {
        this.applyPlateform7daysParserScore = applyPlateform7daysParserScore;
    }

    public Integer getApplyP2pPlateform7daysParserScore() {
        return applyP2pPlateform7daysParserScore;
    }

    public void setApplyP2pPlateform7daysParserScore(Integer applyP2pPlateform7daysParserScore) {
        this.applyP2pPlateform7daysParserScore = applyP2pPlateform7daysParserScore;
    }

    public Integer getApplyPlateform3MParserScore() {
        return applyPlateform3MParserScore;
    }

    public void setApplyPlateform3MParserScore(Integer applyPlateform3MParserScore) {
        this.applyPlateform3MParserScore = applyPlateform3MParserScore;
    }

    public Integer getApplyP2pPlateform3MParserScore() {
        return applyP2pPlateform3MParserScore;
    }

    public void setApplyP2pPlateform3MParserScore(Integer applyP2pPlateform3MParserScore) {
        this.applyP2pPlateform3MParserScore = applyP2pPlateform3MParserScore;
    }

    public Integer getRealNameParserScore() {
        return realNameParserScore;
    }

    public void setRealNameParserScore(Integer realNameParserScore) {
        this.realNameParserScore = realNameParserScore;
    }

    public Integer getPhoneUsedTimeParserScore() {
        return phoneUsedTimeParserScore;
    }

    public void setPhoneUsedTimeParserScore(Integer phoneUsedTimeParserScore) {
        this.phoneUsedTimeParserScore = phoneUsedTimeParserScore;
    }

    public Integer getSearchedOrgCntParserScore() {
        return searchedOrgCntParserScore;
    }

    public void setSearchedOrgCntParserScore(Integer searchedOrgCntParserScore) {
        this.searchedOrgCntParserScore = searchedOrgCntParserScore;
    }

    public Double getRegisterOrgCntParserScore() {
        return registerOrgCntParserScore;
    }

    public void setRegisterOrgCntParserScore(Double registerOrgCntParserScore) {
        this.registerOrgCntParserScore = registerOrgCntParserScore;
    }

    public Double getIdcardWithOtherPhonesParserScore() {
        return idcardWithOtherPhonesParserScore;
    }

    public void setIdcardWithOtherPhonesParserScore(Double idcardWithOtherPhonesParserScore) {
        this.idcardWithOtherPhonesParserScore = idcardWithOtherPhonesParserScore;
    }

    public Double getIdcardWithOtherNamesParserScore() {
        return idcardWithOtherNamesParserScore;
    }

    public void setIdcardWithOtherNamesParserScore(Double idcardWithOtherNamesParserScore) {
        this.idcardWithOtherNamesParserScore = idcardWithOtherNamesParserScore;
    }

    public Double getPhoneWithOtherIdcardsParserScore() {
        return phoneWithOtherIdcardsParserScore;
    }

    public void setPhoneWithOtherIdcardsParserScore(Double phoneWithOtherIdcardsParserScore) {
        this.phoneWithOtherIdcardsParserScore = phoneWithOtherIdcardsParserScore;
    }

    public Double getPhoneWithOtherNamesParserScore() {
        return phoneWithOtherNamesParserScore;
    }

    public void setPhoneWithOtherNamesParserScore(Double phoneWithOtherNamesParserScore) {
        this.phoneWithOtherNamesParserScore = phoneWithOtherNamesParserScore;
    }

    public Double getBlackInfoPhoneGrayScoreParserScore() {
        return blackInfoPhoneGrayScoreParserScore;
    }

    public void setBlackInfoPhoneGrayScoreParserScore(Double blackInfoPhoneGrayScoreParserScore) {
        this.blackInfoPhoneGrayScoreParserScore = blackInfoPhoneGrayScoreParserScore;
    }

    public Integer getWorkPlaceConsistentParserScore() {
        return workPlaceConsistentParserScore;
    }

    public void setWorkPlaceConsistentParserScore(Integer workPlaceConsistentParserScore) {
        this.workPlaceConsistentParserScore = workPlaceConsistentParserScore;
    }

    public Integer getContact1MobileRankParserScore() {
        return contact1MobileRankParserScore;
    }

    public void setContact1MobileRankParserScore(Integer contact1MobileRankParserScore) {
        this.contact1MobileRankParserScore = contact1MobileRankParserScore;
    }

    public Integer getContact2MobileRankParserScore() {
        return contact2MobileRankParserScore;
    }

    public void setContact2MobileRankParserScore(Integer contact2MobileRankParserScore) {
        this.contact2MobileRankParserScore = contact2MobileRankParserScore;
    }

    public Integer getLoanOrgCallCntParserScore() {
        return loanOrgCallCntParserScore;
    }

    public void setLoanOrgCallCntParserScore(Integer loanOrgCallCntParserScore) {
        this.loanOrgCallCntParserScore = loanOrgCallCntParserScore;
    }

    public Integer getContactLoanOrgParserScore() {
        return contactLoanOrgParserScore;
    }

    public void setContactLoanOrgParserScore(Integer contactLoanOrgParserScore) {
        this.contactLoanOrgParserScore = contactLoanOrgParserScore;
    }

    public Integer getLoanOrgCallCntTimesParserScore() {
        return loanOrgCallCntTimesParserScore;
    }

    public void setLoanOrgCallCntTimesParserScore(Integer loanOrgCallCntTimesParserScore) {
        this.loanOrgCallCntTimesParserScore = loanOrgCallCntTimesParserScore;
    }

    public Integer getBankLoanOrgCallCntParserScore() {
        return bankLoanOrgCallCntParserScore;
    }

    public void setBankLoanOrgCallCntParserScore(Integer bankLoanOrgCallCntParserScore) {
        this.bankLoanOrgCallCntParserScore = bankLoanOrgCallCntParserScore;
    }

    public Integer getBankLoanOrgCallCntTimesParserScore() {
        return bankLoanOrgCallCntTimesParserScore;
    }

    public void setBankLoanOrgCallCntTimesParserScore(Integer bankLoanOrgCallCntTimesParserScore) {
        this.bankLoanOrgCallCntTimesParserScore = bankLoanOrgCallCntTimesParserScore;
    }

    public Integer getCreditLoanOrgCallCntParserScore() {
        return creditLoanOrgCallCntParserScore;
    }

    public void setCreditLoanOrgCallCntParserScore(Integer creditLoanOrgCallCntParserScore) {
        this.creditLoanOrgCallCntParserScore = creditLoanOrgCallCntParserScore;
    }

    public Integer getCreditLoanOrgCallCntTimesParserScore() {
        return creditLoanOrgCallCntTimesParserScore;
    }

    public void setCreditLoanOrgCallCntTimesParserScore(Integer creditLoanOrgCallCntTimesParserScore) {
        this.creditLoanOrgCallCntTimesParserScore = creditLoanOrgCallCntTimesParserScore;
    }

    public Double getLawyerContactedCntParserScore() {
        return lawyerContactedCntParserScore;
    }

    public void setLawyerContactedCntParserScore(Double lawyerContactedCntParserScore) {
        this.lawyerContactedCntParserScore = lawyerContactedCntParserScore;
    }

    public Double getContacted_110_CntParserScore() {
        return contacted_110_CntParserScore;
    }

    public void setContacted_110_CntParserScore(Double contacted_110_CntParserScore) {
        this.contacted_110_CntParserScore = contacted_110_CntParserScore;
    }

    public Integer getEachOtherContactsCntParserScore() {
        return eachOtherContactsCntParserScore;
    }

    public void setEachOtherContactsCntParserScore(Integer eachOtherContactsCntParserScore) {
        this.eachOtherContactsCntParserScore = eachOtherContactsCntParserScore;
    }

    public Integer getSilentDaysSumParserScore() {
        return silentDaysSumParserScore;
    }

    public void setSilentDaysSumParserScore(Integer silentDaysSumParserScore) {
        this.silentDaysSumParserScore = silentDaysSumParserScore;
    }

    public Double getTripTimesParserScore() {
        return tripTimesParserScore;
    }

    public void setTripTimesParserScore(Double tripTimesParserScore) {
        this.tripTimesParserScore = tripTimesParserScore;
    }

    public String getPlaceRegister() {
        return placeRegister;
    }

    public void setPlaceRegister(String placeRegister) {
        this.placeRegister = placeRegister;
    }


    public String getNumberIdCard() {
        return numberIdCard;
    }

    public void setNumberIdCard(String numberIdCard) {
        this.numberIdCard = numberIdCard;
    }

    public String getRealnameUser() {
        return realnameUser;
    }

    public void setRealnameUser(String realnameUser) {
        this.realnameUser = realnameUser;
    }

    public String getNumberCellphone() {
        return numberCellphone;
    }

    public void setNumberCellphone(String numberCellphone) {
        this.numberCellphone = numberCellphone;
    }

    public Integer getPhoneUsedTimeByDetails() {
        return phoneUsedTimeByDetails;
    }

    public void setPhoneUsedTimeByDetails(Integer phoneUsedTimeByDetails) {
        this.phoneUsedTimeByDetails = phoneUsedTimeByDetails;
    }

    public Integer getPhoneUsedTimeByRegister() {
        return phoneUsedTimeByRegister;
    }

    public void setPhoneUsedTimeByRegister(Integer phoneUsedTimeByRegister) {
        this.phoneUsedTimeByRegister = phoneUsedTimeByRegister;
    }

    public String getZmWatchListHitRiskCode() {
        return zmWatchListHitRiskCode;
    }

    public void setZmWatchListHitRiskCode(String zmWatchListHitRiskCode) {
        this.zmWatchListHitRiskCode = zmWatchListHitRiskCode;
    }

    public String getZmWatchListOverdueTimes12Mths() {
        return zmWatchListOverdueTimes12Mths;
    }

    public void setZmWatchListOverdueTimes12Mths(String zmWatchListOverdueTimes12Mths) {
        this.zmWatchListOverdueTimes12Mths = zmWatchListOverdueTimes12Mths;
    }

    public String getZmWatchListOverdueTimes24Mths() {
        return zmWatchListOverdueTimes24Mths;
    }

    public void setZmWatchListOverdueTimes24Mths(String zmWatchListOverdueTimes24Mths) {
        this.zmWatchListOverdueTimes24Mths = zmWatchListOverdueTimes24Mths;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
