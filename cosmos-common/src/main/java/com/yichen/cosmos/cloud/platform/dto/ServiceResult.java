package com.yichen.cosmos.cloud.platform.dto;

import com.alibaba.fastjson.JSON;

/**
 * Created by Lizhengxian on 2017/3/23.
 */
public class ServiceResult {
    private int code;
    private String msg;
    private Object data;

    public ServiceResult() {
    }

    public ServiceResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public ServiceResult setData(Object data) {
        this.data = data;
        return this;
    }
}
