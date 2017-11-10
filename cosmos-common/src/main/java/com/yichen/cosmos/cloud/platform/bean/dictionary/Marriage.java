package com.yichen.cosmos.cloud.platform.bean.dictionary;


/**
 * Created by Lizhengxian on 2017/3/28.
 * 婚姻状况
 */
public class Marriage {

    private Integer id;
    private String resume;
    private String marriageStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }
}
