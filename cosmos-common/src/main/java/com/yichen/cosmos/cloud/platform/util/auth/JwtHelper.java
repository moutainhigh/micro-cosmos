package com.yichen.cosmos.cloud.platform.util.auth;

import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.constants.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT工具类。用于生成用户的TOKEN信息以及解析
 * <p>
 * Created by Linq on 2017/3/24 17:37.
 */
public class JwtHelper {


    /**
     * 生成TOKEN
     *
     * @param id        ID
     * @param subject   存储的信息
     * @param ttlMillis 过期时间
     * @return 创建JWT
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //生成JWT
        return builder.compact();
    }


    /**
     * 解密
     *
     * @param jwt 生成的JWT（token）
     * @return Claims
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = null;
        try {
            SecretKey key = generalKey();
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 生成subject信息
     *
     * @param t subject 对象
     * @return json string
     */
    public static <T> String generalSubject(T t) {
        return JSON.toJSONString(t);
    }


    /**
     * 生成加密KEY
     *
     * @return 加密key
     */
    private static SecretKey generalKey() {
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

}
