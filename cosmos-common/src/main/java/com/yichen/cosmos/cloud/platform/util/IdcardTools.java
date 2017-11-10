package com.yichen.cosmos.cloud.platform.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by Lizhengxian on 2017/4/14.
 */
public class IdcardTools {

    /**
     * 中国公民身份证号码最小长度。
     */
    public final int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    public final int CHINA_ID_MAX_LENGTH = 18;

    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "未知";

        int length = idCard.length();
        String sex = idCard.substring(length - 2, length - 1);
        if (Integer.parseInt(sex) % 2 != 0) {
            sGender = "1";//男
        } else {
            sGender = "2";//女
        }
        return sGender;
    }

    public static void main(String[] args) {
        String idcard = "330501199408110213";
        String sex = getGenderByIdCard(idcard);
        System.out.println("性别:" + sex);
        int age = getAgeByIdCard(idcard);
        System.out.println("年龄:" + age);
        Short nian = getYearByIdCard(idcard);
        Short yue = getMonthByIdCard(idcard);
        Short ri = getDateByIdCard(idcard);
        System.out.println(nian + "年" + yue + "月" + ri + "日");

        String sr = getBirthByIdCard(idcard);
        System.out.println("生日:" + sr);
        LocalDateTime of = LocalDateTime.of((int) nian, Month.of((int) yue), (int) ri, 0, 0);
        System.out.println(of.toString());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = of.format(dateTimeFormatter);
        System.out.println(format);
    }
}
