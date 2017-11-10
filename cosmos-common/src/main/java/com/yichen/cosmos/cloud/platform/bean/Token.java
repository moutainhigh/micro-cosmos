package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月11日 下午3:01:18
 */
public class Token implements Serializable {
    private static final long serialVersionUID = 7632521317640827840L;

    private String userId;
    private String account;
    private Map<String, String> attachedMap;
    /**
     * 当前Token对象解析对应的token
     */
    private String token;
    /**
     * token超时
     * 离线时，isdeline为true
     * 有效期内，isdeline为false
     */
    private boolean isdeline = true;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsdeline() {
        return isdeline;
    }

    public void setIsdeline(boolean isdeline) {
        this.isdeline = isdeline;
    }

    public Map<String, String> getAttachedMap() {
        return attachedMap;
    }

    public void setAttachedMap(Map<String, String> attachedMap) {
        this.attachedMap = attachedMap;
    }
}
