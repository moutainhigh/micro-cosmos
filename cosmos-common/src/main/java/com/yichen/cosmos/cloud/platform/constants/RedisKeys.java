package com.yichen.cosmos.cloud.platform.constants;

import com.yichen.cosmos.cloud.platform.enums.JwtPlatform;

/**
 * Redis中的KEY，所有Redis中的KEY统一管理
 * <p>
 * Created by Linq on 2017/3/27 18:59.
 */
public class RedisKeys {


    //WEB用户登录后保存的key
    private static final String WEB_ACCESS_TOKEN_KEY = "web_access_token:key_%s";
    public static final long WEB_ACCESS_TOKEN_EXPIRE = 24 * 3600 * 1000L;

    private static final String SYSTEM_COUPON_NUM = "coupon_num:%s";

    //用户授权TOKEN，KEY
    private static final String ACCESS_TOKEN_KEY = "app_token:access_token:key_%s";
    public static final long ACCESS_TOKEN_EXPIRE = 3600 * 1000L;
    //用户刷新TOKEN，KEY
    private static final String REFRESH_TOKEN_KEY = "app_token:refresh_token:key_%s";
    public static final long REFRESH_TOKEN_EXPIRE = 24 * 3600 * 1000L;
    public static final long OLD_REFRESH_TOKEN_EXPIRE = 2 * 3600 * 1000L;

    private static final String USER_PLATFORM_KEY = "app_token:user_platform_token:key_%s_%s";
    public static final long USER_PLATFORM_EXPIRE = 30 * 24 * 3600 * 1000L;

    private static final String SHORT_TIME_SUBMIT_AGAIN = "submit_again:_%s:%s:_%s";

    //验证码存储key
    public static final String VERITY_CODE = "verity_code:key_";
    public static final String VERITY_CODE_NUM = "verity_code:send_num:key_";
    public static final long VERITY_CODE_EXPIRE = 30 * 60 * 1000L;
    //系统编号
    public static final String SYSTEM_CODE = "system_code:key_";
    //地区代码
    public static final String AREA_CODE = "area_code";


    public static String getShortTimeSubmitAgain(String prefix, String key, String suffix) {
        return String.format(SHORT_TIME_SUBMIT_AGAIN, prefix, key, suffix);
    }

    public static String GET_WEB_ACCESS_TOKEN_KEY(String token) {
        return String.format(WEB_ACCESS_TOKEN_KEY, token);
    }

    public static String GET_ACCESS_TOKEN_KEY(String token) {
        return String.format(ACCESS_TOKEN_KEY, token);
    }

    public static String GET_REFRESH_TOKEN_KEY(String token) {
        return String.format(REFRESH_TOKEN_KEY, token);
    }

    public static String GET_USER_PLATFORM_KEY(String userId, JwtPlatform platform) {
        return String.format(USER_PLATFORM_KEY, userId, platform.getPlatform());
    }

    public static String getSystemCouponNum(String key) {
        return String.format(SYSTEM_COUPON_NUM, key);
    }

}
