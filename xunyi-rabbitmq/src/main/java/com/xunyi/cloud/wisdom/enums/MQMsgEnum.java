package com.xunyi.cloud.wisdom.enums;

/**
 * Created by thomas.su on 2017/11/10 18:08.
 * mq消息类别
 */
public enum MQMsgEnum {

    DEPLOY_STRATEGY("1000", "策略发布，加载流程"),
    CLOSE_STRATEGY("2000", "策略关闭，删除流程"),;
    private String code;
    private String desc;

    MQMsgEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
