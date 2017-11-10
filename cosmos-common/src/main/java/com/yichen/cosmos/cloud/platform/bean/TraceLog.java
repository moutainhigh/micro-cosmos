package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;

/**
 * Created by thomas on 2017/2/22.
 */
public class TraceLog implements Serializable {

    private static final long serialVersionUID = 5858960023241031184L;
    private String logId;
    private String userId;
    private String context;
    private String ipAddress;
    private String createTime;
    private String updateTime;

    public static class Builder {
        private String logId;
        private String userId;
        private String context;
        private String ipAddress;

        public Builder() {
        }

        public Builder logId(String logId) {
            this.logId = logId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder context(String context) {
            this.context = context;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public TraceLog build() {
            return new TraceLog(this);
        }
    }

    private TraceLog(Builder b) {
        this.logId = b.logId;
        this.userId = b.userId;
        this.context = b.context;
        this.ipAddress = b.ipAddress;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
