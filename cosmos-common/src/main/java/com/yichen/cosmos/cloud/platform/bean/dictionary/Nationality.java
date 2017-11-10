package com.yichen.cosmos.cloud.platform.bean.dictionary;


/**
 * Created by Lizhengxian on 2017/3/28.
 * 名族字典
 */
public class Nationality {

    private Integer id;
    private String resume;
    private String nationalityName;

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

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
