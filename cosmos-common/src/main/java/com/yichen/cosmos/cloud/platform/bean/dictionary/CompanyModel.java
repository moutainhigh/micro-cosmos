package com.yichen.cosmos.cloud.platform.bean.dictionary;

/**
 * 公司性质
 */
public class CompanyModel {

    private Integer id;
    /**
     * 英文简称
     **/
    private String resume;
    /**
     * 公司性质 名称
     **/
    private String companyModelName;


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

    public String getCompanyModelName() {
        return companyModelName;
    }

    public void setCompanyModelName(String companyModelName) {
        this.companyModelName = companyModelName;
    }
}
