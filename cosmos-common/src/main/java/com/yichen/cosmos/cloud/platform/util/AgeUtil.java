package com.yichen.cosmos.cloud.platform.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 年龄需要用到周岁（月、天），这种比较具体的时间，如果小于1岁，需要具体到月，小于1月，具体到天，不足一天的按一天算,只是将当前年份减去出生日期的年份往往不准确
 * Created by thomas.su on 2017/2/6.
 */
public class AgeUtil {
    private static final Logger logger = LoggerFactory.getLogger(AgeUtil.class);

    public static int getAge(Date dateOfBirth) {

        int age = 0;

        Calendar born = Calendar.getInstance();

        Calendar now = Calendar.getInstance();

        if (dateOfBirth != null) {

            now.setTime(new Date());

            born.setTime(dateOfBirth);

            if (born.after(now)) {

                throw new IllegalArgumentException("年龄不能超过当前日期");

            }

            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);

            int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);

            int bornDayOfYear = born.get(Calendar.DAY_OF_YEAR);

            logger.info("年龄计算 nowDayOfYear:{},bornDayOfYear:{}", nowDayOfYear, bornDayOfYear);

            if (nowDayOfYear < bornDayOfYear) {

                age -= 1;

            }
        }
        return age;
    }

    public static int getAgeByCardNo(String numberIdCard) {
        try {
            String birthYear = numberIdCard.substring(6, 14);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date bothDate = simpleDateFormat.parse(birthYear);

            int age = getAge(bothDate);
            return age;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    public static void main(String[] args) throws Exception {

        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Date mydate = myFormatter.parse("1999-06-21");

        System.out.println(getAge(mydate));

    }

}
