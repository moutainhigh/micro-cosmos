package com.yichen.cosmos.cloud.platform.bean.credit.score;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.bahaviour.*;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.basicinfo.*;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.connection.Connection;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.connection.Moments;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.creditinfo.*;
import com.yichen.cosmos.cloud.platform.bean.rule_bean.promise.*;
import com.yichen.cosmos.cloud.platform.util.BaseCreditScoreTools;
import com.yichen.cosmos.cloud.platform.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Lizhengxian on 2017/3/15.
 */
public class BaseCreditScore implements Serializable {
    private final static transient Logger logger = LoggerFactory.getLogger(BaseCreditScore.class);

    //详细信息
    private CallRecord callRecord;
    private OnlineBuyRecord onlineBuyRecord;
    private PhoneBuyRecord phoneBuyRecord;
    private RiskRecord riskRecord;
    private TripRecord tripRecord;
    private BasicInfo basicInfo;
    private DiplomaInfo diplomaInfo;
    private JobCertificationInfo jobCertificationInfo;
    private LivingInfo livingInfo;
    private ReliabilityInfo reliabilityInfo;
    private StudyInfo studyInfo;
    private List<Connection> connection = new ArrayList<>();
    private Moments moments;
    private BlackList blackList;
    private CarRecord carRecord;
    private CourtDishonesty courtDishonesty;
    private CreditRecord creditRecord;
    private GjjRecord gjjRecord;
    private TradeCredit tradeCredit;
    private AccountInfo accountInfo;
    private CarAndHouse carAndHouse;
    private CarInfo carInfo;
    private CreditCard creditCard;
    private GjjLoan gjjLoan;
    private GjjSave gjjSave;
    private JobInfo jobInfo;


    public static void main(String[] args) {
//        Class<BaseCreditScore> creditScoreClass = BaseCreditScore.class;
//        Field[] declaredFields = creditScoreClass.getDeclaredFields();
//
//        for(Field field : declaredFields){
//            StringBuilder sb = new StringBuilder(field.getName());
//            char c = sb.charAt(0);
//            String cs = c+"";
//            sb.deleteCharAt(0);
//            sb.insert(0,cs.toUpperCase());
//            System.out.println(sb);
//        }

    }

    /**
     * 获取返回列表属性的字段名称
     *
     * @return
     */
    public static List<String> getListFieldName() {
        List<String> fieldNameList = new ArrayList<>();
        Field[] declaredFields = BaseCreditScore.class.getDeclaredFields();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            if (!fieldName.equals("logger")) {

                if (field.getType().getName().contains("java.util.List")) {
                    fieldNameList.add(fieldName);
                }
            }
        }

        return fieldNameList;
    }

    public BaseCreditScore() {
        Field[] declaredFields = BaseCreditScore.class.getDeclaredFields();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            if (!fieldName.equals("logger")) {
                try {

                    if (field.getType().getName().contains("java.util.List")) {
                        continue;
                    }
                    Object fieldInstance = field.getType().newInstance();
                    field.set(this, fieldInstance);
                    Method[] methods = fieldInstance.getClass().getMethods();
                    for (Method method : methods) {
                        if (method.getName().startsWith("set")) {
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            String name = parameterTypes[0].getName();
                            try {
                                switch (name) {
                                    case "java.lang.String":
                                        method.invoke(fieldInstance, "");
                                        break;
                                    case "java.lang.Integer":
                                        method.invoke(fieldInstance, 0);
                                        break;
                                    case "java.lang.Double":
                                        method.invoke(fieldInstance, 0d);
                                        break;
                                    case "java.lang.Boolean":
                                        method.invoke(fieldInstance, false);
                                }
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                logger.error("实例化子对象出错", e);
                            }
                        }
                    }

                } catch (IllegalAccessException | InstantiationException e) {
                    logger.error("实例化对象出错", e);
                }
            }
        }
    }


    public HashMap<String, Object> toHashMap() {
        return BaseCreditScoreTools.parseToHashMap(this);
    }

    public TreeMap<String, Object> toTreeMap() {
        return BaseCreditScoreTools.parseToTreeMap(this);
    }


    public JSONObject toJSONObject() {
        return JSON.parseObject(this.toString());
    }

    public Object getValueByFormNameAndFieldName(String formName, String fieldName) {
        String formNameToLower = StringTools.firstToLowerCase(formName);
        String fieldNameToLower = StringTools.firstToLowerCase(fieldName);
        JSONObject jsonObject = this.toJSONObject();
        JSONObject form = jsonObject.getJSONObject(formNameToLower);
        if (form != null) {
            Object object = form.get(fieldNameToLower);
            return object;
        }
        return null;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public CallRecord getCallRecord() {
        return callRecord;
    }

    public void setCallRecord(CallRecord callRecord) {
        this.callRecord = callRecord;
    }

    public OnlineBuyRecord getOnlineBuyRecord() {
        return onlineBuyRecord;
    }

    public void setOnlineBuyRecord(OnlineBuyRecord onlineBuyRecord) {
        this.onlineBuyRecord = onlineBuyRecord;
    }

    public PhoneBuyRecord getPhoneBuyRecord() {
        return phoneBuyRecord;
    }

    public void setPhoneBuyRecord(PhoneBuyRecord phoneBuyRecord) {
        this.phoneBuyRecord = phoneBuyRecord;
    }

    public RiskRecord getRiskRecord() {
        return riskRecord;
    }

    public void setRiskRecord(RiskRecord riskRecord) {
        this.riskRecord = riskRecord;
    }

    public TripRecord getTripRecord() {
        return tripRecord;
    }

    public void setTripRecord(TripRecord tripRecord) {
        this.tripRecord = tripRecord;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public JobCertificationInfo getJobCertificationInfo() {
        return jobCertificationInfo;
    }

    public void setJobCertificationInfo(JobCertificationInfo jobCertificationInfo) {
        this.jobCertificationInfo = jobCertificationInfo;
    }

    public LivingInfo getLivingInfo() {
        return livingInfo;
    }

    public void setLivingInfo(LivingInfo livingInfo) {
        this.livingInfo = livingInfo;
    }

    public ReliabilityInfo getReliabilityInfo() {
        return reliabilityInfo;
    }

    public void setReliabilityInfo(ReliabilityInfo reliabilityInfo) {
        this.reliabilityInfo = reliabilityInfo;
    }

    public List<Connection> getConnection() {
        return connection;
    }

    public void setConnection(List<Connection> connection) {
        this.connection = connection;
    }

    public Moments getMoments() {
        return moments;
    }

    public void setMoments(Moments moments) {
        this.moments = moments;
    }

    public BlackList getBlackList() {
        return blackList;
    }

    public void setBlackList(BlackList blackList) {
        this.blackList = blackList;
    }

    public CarRecord getCarRecord() {
        return carRecord;
    }

    public void setCarRecord(CarRecord carRecord) {
        this.carRecord = carRecord;
    }

    public CourtDishonesty getCourtDishonesty() {
        return courtDishonesty;
    }

    public void setCourtDishonesty(CourtDishonesty courtDishonesty) {
        this.courtDishonesty = courtDishonesty;
    }

    public CreditRecord getCreditRecord() {
        return creditRecord;
    }

    public void setCreditRecord(CreditRecord creditRecord) {
        this.creditRecord = creditRecord;
    }

    public GjjRecord getGjjRecord() {
        return gjjRecord;
    }

    public void setGjjRecord(GjjRecord gjjRecord) {
        this.gjjRecord = gjjRecord;
    }

    public TradeCredit getTradeCredit() {
        return tradeCredit;
    }

    public void setTradeCredit(TradeCredit tradeCredit) {
        this.tradeCredit = tradeCredit;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public CarAndHouse getCarAndHouse() {
        return carAndHouse;
    }

    public void setCarAndHouse(CarAndHouse carAndHouse) {
        this.carAndHouse = carAndHouse;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public GjjLoan getGjjLoan() {
        return gjjLoan;
    }

    public void setGjjLoan(GjjLoan gjjLoan) {
        this.gjjLoan = gjjLoan;
    }

    public GjjSave getGjjSave() {
        return gjjSave;
    }

    public void setGjjSave(GjjSave gjjSave) {
        this.gjjSave = gjjSave;
    }

    public JobInfo getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    public DiplomaInfo getDiplomaInfo() {
        return diplomaInfo;
    }

    public void setDiplomaInfo(DiplomaInfo diplomaInfo) {
        this.diplomaInfo = diplomaInfo;
    }

    public StudyInfo getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(StudyInfo studyInfo) {
        this.studyInfo = studyInfo;
    }
}
