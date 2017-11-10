package com.yichen.cosmos.cloud.platform.enums;

/**
 * 账号登录平台
 * <p>
 * Created by Linq on 2017/7/7 10:24.
 */
public enum JwtPlatform {

    WeChat("wx", "微信公众号"),
    WXSS("wxss", "微信小程序"),
    PC("pc", "PC端"),
    Android("android", "安卓"),
    IOS("ios", "IOS"),
    APP("app", "app端"),
    WAP("wap", "WAP"),

    NULL("_", "未知"),;


    private String platform;
    private String desc;


    JwtPlatform(String platform, String desc) {
        this.platform = platform;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getPlatform() {
        return platform;
    }


    public static JwtPlatform getByType(String type) {
        for (JwtPlatform jwtPlatform : JwtPlatform.values()) {
            if (jwtPlatform.platform.equalsIgnoreCase(type)) return jwtPlatform;
        }
        return NULL;
    }

}
