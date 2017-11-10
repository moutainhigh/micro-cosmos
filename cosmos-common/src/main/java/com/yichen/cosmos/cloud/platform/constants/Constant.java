package com.yichen.cosmos.cloud.platform.constants;

/**
 * 常量
 * <p>
 * Created by Linq on 2017/3/24 17:53.
 */
public class Constant {

    //token类型
    public static final String TOKEN_TYPE = "bearer";
    //JWT ID
    public static final String JWT_ID = "jwt";
    //JWT 秘钥
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    //Token过期时间
    public static final long JWT_TTL = 24 * 60 * 60 * 1000;  //millisecond    1小时
    //RefreshToken过期时间
    public static final long JWT_REFRESH_TTL = 7 * 24 * 60 * 60 * 1000;  //millisecond  7天


}
