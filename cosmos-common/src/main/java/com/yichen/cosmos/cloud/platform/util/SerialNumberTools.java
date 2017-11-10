package com.yichen.cosmos.cloud.platform.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SerialNumberTools {

    private static String count = "0000";
    private static String dateValue = "20131115";

    /**
     * 产生流水号
     */
    public static String getMoveOrderNo() {
        long No = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        String nowdate = sdf.format(new Date());
        No = Long.parseLong(nowdate);
//		if (!(String.valueOf(No)).equals(dateValue)) {
//			count = "0000";
//			dateValue = String.valueOf(No);
//		}
        int random = ThreadLocalRandom.current().nextInt(89999) + 10000;
        String num = String.valueOf(No) + random;
//		num += getNo(count);
        // num = "CB" + num;

        return num;
    }

    /**
     * 获取撤展单序列号
     */
    public synchronized static String getMoveOrderNo(String serialNum) {
        String nyr = StringUtils.substring(serialNum, 2, 10); // 获取年月日字符串
        String countV = StringUtils.substring(serialNum, 10); // 获取流水号
        if (Integer.valueOf(countV) > Integer.valueOf(count)) {
            dateValue = nyr;
            count = String.valueOf(countV);
        }
        return getMoveOrderNo();
    }

    /**
     * 返回当天的订单数+1
     */
    public static String getNo(String s) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < 4; j++) {
            rs = "0" + rs;
        }
        count = rs;
        return rs;
    }

    public static void main(String[] args) {
        // System.out.println(getUUID());
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
//            int l = random.nextInt(89999) + 10000;

//            System.out.println("l = " + l);
//            System.out.println("t = " + (ThreadLocalRandom.current().nextInt(89999) + 10000));
            System.out.println(getMoveOrderNo());
        }
    }


}
