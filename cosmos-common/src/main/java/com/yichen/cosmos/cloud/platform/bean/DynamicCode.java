package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;

/**
 * 动态验证码
 *
 * @author thomas.su
 * @version 1.0
 * @String 2016年8月3日 上午10:30:37
 */
public class DynamicCode implements Serializable {

    private static final long serialVersionUID = -2829931951213792026L;

    private Long id;
    /**
     * 手机号
     **/
    private String phone;
    /**
     * 验证码类型名称
     **/
    private String codeName;
    /**
     * 验证码类型
     **/
    private String codeType;
    /**
     * 动态验证码
     **/
    private String dynamicCode;
    /**
     * 发送状态
     **/
    private String sendState;
    /**
     * 发送状态描述
     **/
    private String stateDes;
    /**
     * 平台
     **/
    private String plateform;
    /**
     * 发送时间
     **/
    private String sendTime;
    /**
     * ip地址
     **/
    private String ipAddress;
    /**
     * 更新时间
     **/
    private String upStringTime;
    /**
     * 创建时间
     **/
    private String createTime;

    public Long getId() {
        return id;
    }

    public DynamicCode id(Long id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public DynamicCode phone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCodeName() {
        return codeName;
    }

    public DynamicCode codeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public String getCodeType() {
        return codeType;
    }

    public DynamicCode codeType(String codeType) {
        this.codeType = codeType;
        return this;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public DynamicCode dynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
        return this;
    }

    public String getSendState() {
        return sendState;
    }

    public DynamicCode sendState(String sendState) {
        this.sendState = sendState;
        return this;
    }

    public String getStateDes() {
        return stateDes;
    }

    public DynamicCode stateDes(String stateDes) {
        this.stateDes = stateDes;
        return this;
    }

    public String getPlateform() {
        return plateform;
    }

    public DynamicCode plateform(String plateform) {
        this.plateform = plateform;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public DynamicCode sendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public DynamicCode ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getUpStringTime() {
        return upStringTime;
    }

    public DynamicCode upStringTime(String upStringTime) {
        this.upStringTime = upStringTime;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public DynamicCode createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
