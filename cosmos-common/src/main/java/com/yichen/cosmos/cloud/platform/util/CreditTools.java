package com.yichen.cosmos.cloud.platform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class CreditTools {
    private static final Logger logger = LoggerFactory.getLogger(CreditTools.class);

    /**
     * 获取昵称
     *
     * @return
     */
    public static String genNickName(String nickPrefix) {
        String serial = UUID.randomUUID().toString().trim().replaceAll("-", "");

        String name = serial.length() > 20 ? serial.substring(0, 20) : serial;

        StringBuffer nickName = new StringBuffer(nickPrefix);
        nickName.append(name);

        return nickName.toString();
    }


    /**
     * 获取随机验证码
     *
     * @return
     */
    public static String getRandomCode(String length) {
        Integer len = Integer.parseInt(length);
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            result.append(random.nextInt(10));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomCode("6"));
        }
    }
}
