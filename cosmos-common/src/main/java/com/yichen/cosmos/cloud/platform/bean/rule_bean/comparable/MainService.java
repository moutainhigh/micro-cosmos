package com.yichen.cosmos.cloud.platform.bean.rule_bean.comparable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * Created by Lizhengxian on 2017/3/15.
 * 封装通话行为，以便于排序
 */
public class MainService implements Comparable {
    private JSONArray serviceDetails;
    private Integer totalServiceCnt;
    private String companyType;
    private String companyName;

    public JSONArray getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(JSONArray serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public Integer getTotalServiceCnt() {
        return totalServiceCnt;
    }

    public void setTotalServiceCnt(Integer totalServiceCnt) {
        this.totalServiceCnt = totalServiceCnt;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MainService) {
            if (this.getTotalServiceCnt() > ((MainService) o).getTotalServiceCnt()) {
                return 1;
            } else if (this.getTotalServiceCnt() == ((MainService) o).getTotalServiceCnt()) {
                return 0;
            } else {
                return -1;
            }
        }
        if (o instanceof Integer) {
            if (this.getTotalServiceCnt() > (Integer) o) {
                return 1;
            } else if (this.getTotalServiceCnt().equals((Integer) o)) {
                return 0;
            } else {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
