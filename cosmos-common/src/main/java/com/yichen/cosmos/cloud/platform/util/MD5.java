package com.yichen.cosmos.cloud.platform.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static final String HEX = "0123456789abcdef";

    public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = text.getBytes("utf-8");
            byte[] output = md.digest(input);
//			System.out.println(output.length);
            StringBuilder sb = new StringBuilder(32);
            for (byte b : output) {
//				byte high4 = (byte) (b >>> 4 & 0x0f);
//				byte low4 = (byte) (b & 0x0f);
//				System.out.println(b + " " + high4 + " " + low4);
                // unsigned bit shift, MSB will be filled with zeros
                sb.append(HEX.charAt(b >>> 4 & 0x0f));
                sb.append(HEX.charAt(b & 0x0f));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//		long t = System.currentTimeMillis();
//		String key = "xiaomifengTRc2427e45-3a71-4e6e-8190-3a150d6b0617TR" + t;
//		System.out.println(key + " " + encrypt(key) + " " + t);

        String s = "1";
        System.out.println(encrypt(s));
    }

}
