package com.yichen.cosmos.cloud.platform.enums;

/**
 * Created by thomas.su on 2017/11/8 16:56.
 * 判断报告是否成功查询
 */
public enum SuccessEnum {

    先知("10100", "先知成功返回"),;
    // 成员变量
    private String code;
    private String msg;

    // 构造方法
    SuccessEnum(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static boolean isSuccess(String bkcode) {
        for (SuccessEnum successEnum : SuccessEnum.values()) {
            String code = successEnum.getCode();
            if (code.equalsIgnoreCase(bkcode)) {
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
