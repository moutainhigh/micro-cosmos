package com.yichen.cosmos.cloud.platform.enums;

/**
 * 动态验证码
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月2日 下午5:16:38
 */
public enum DynamicCodeEnum {

    DYNAMIC_REGISTER_CODE("1001", "注册动态验证码"),
    DYNAMIC_LOGIN_CODE("1002", "快速登录动态验证码"),
    DYNAMIC_BIND_CODE("1003", "绑定手机号动态验证码"),
    DYNAMIC_FORGETPSD_CODE("1004", "忘记密码动态验证码"),;

    private String code;
    private String name;

    DynamicCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getDynamicCodeName(String dynamicCodeType) {
        for (DynamicCodeEnum dynamic : DynamicCodeEnum.values()) {
            String code = dynamic.getCode();
            if (code.equals(dynamicCodeType)) {
                String name = dynamic.getName();
                return name;
            }
        }

        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
