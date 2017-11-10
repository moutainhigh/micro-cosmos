package com.yichen.cosmos.cloud.platform.bean.product;

import java.util.List;
import java.util.Map;

/**
 * 产品列表使用
 * Created by thomas on 2017/4/27 11:40.
 */
public class ProVersion {
    private String productId;
    private String productName;
    private String state;

    private List<Map<String, String>> proVersionList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Map<String, String>> getProVersionList() {
        return proVersionList;
    }

    public void setProVersionList(List<Map<String, String>> proVersionList) {
        this.proVersionList = proVersionList;
    }
}
