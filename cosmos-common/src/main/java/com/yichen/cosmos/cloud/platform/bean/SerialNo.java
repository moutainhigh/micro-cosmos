package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;

/**
 * 模拟客户端随机码 jsessionId
 * Created by thomas on 2017/4/7 11:22.
 */
public class SerialNo implements Serializable {
    private static final long serialVersionUID = 726774851941324344L;

    private String serialNo;
    private String attached;
    /**
     * 加密后的
     * 当前SerialNo对象解析对应的 encrypSerialNo
     */
    private String encrypSerialNo;
    /**
     * 离线时，isdeline为true
     * 有效期内，isdeline为false
     */
    private boolean isdeline = true;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAttached() {
        return attached;
    }

    public void setAttached(String attached) {
        this.attached = attached;
    }

    public String getEncrypSerialNo() {
        return encrypSerialNo;
    }

    public void setEncrypSerialNo(String encrypSerialNo) {
        this.encrypSerialNo = encrypSerialNo;
    }

    public boolean isdeline() {
        return isdeline;
    }

    public void setIsdeline(boolean isdeline) {
        this.isdeline = isdeline;
    }
}
