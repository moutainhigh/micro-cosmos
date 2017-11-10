package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;

/**
 * Created by thomas on 2017/3/2.
 */
public class WisdomEmailLog implements Serializable {

    private static final long serialVersionUID = 8819719537226104079L;
    private Integer id;
    private String email;
    private String operation;
    private String outTime;
    private String createTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
