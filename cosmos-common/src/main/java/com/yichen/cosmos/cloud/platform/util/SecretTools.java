package com.yichen.cosmos.cloud.platform.util;

/**
 * 账号密码加密工具
 *
 * @author thomas.su
 */
public class SecretTools {

    public static String secrect(String password, String userId) {
        StringBuffer text = new StringBuffer("wisdom_");
        String initValue = text.append(password).append(userId).toString();
        String temp = MD5.encrypt(initValue);

        return temp;
    }

    public static void main(String[] args) {
        String userId = SUID.getUUID();
        System.out.println("userId:" + userId);
        String password = "hzwd@tairanchina.com";

        String serect = secrect(password, userId);
        System.out.println("serect:" + serect);
    }
}
