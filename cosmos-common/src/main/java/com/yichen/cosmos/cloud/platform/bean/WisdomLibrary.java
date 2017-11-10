package com.yichen.cosmos.cloud.platform.bean;


import com.yichen.cosmos.cloud.platform.bean.data.center.WisdomForm;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thomas on 2017/3/9.
 */
public class WisdomLibrary implements Serializable {
    private static final long serialVersionUID = -6756452286260268201L;

    private Integer libId;
    /**
     * 规则库名称
     */
    private String libName;
    /**
     * 1：有效 0：删除
     */
    private Integer flag;
    private String createTime;
    private String updateTime;


    private List<WisdomForm> wisdomFormList;

    public List<WisdomForm> getWisdomFormList() {
        return wisdomFormList;
    }

    public void setWisdomFormList(List<WisdomForm> wisdomFormList) {
        this.wisdomFormList = wisdomFormList;
    }

    public Integer getLibId() {
        return libId;
    }

    public void setLibId(Integer libId) {
        this.libId = libId;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
