package com.tairanchina.fd.loan.entity;

public class AsyncCallbackRecordWithBLOBs extends AsyncCallbackRecord {
    /**
     * post数据
     */
    private String postData;

    /**
     * 备注：比如记录失败原因
     */
    private String remark;

    /**
     * post数据
     * @return post_data post数据
     */
    public String getPostData() {
        return postData;
    }

    /**
     * post数据
     * @param postData post数据
     */
    public void setPostData(String postData) {
        this.postData = postData == null ? null : postData.trim();
    }

    /**
     * 备注：比如记录失败原因
     * @return remark 备注：比如记录失败原因
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注：比如记录失败原因
     * @param remark 备注：比如记录失败原因
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}