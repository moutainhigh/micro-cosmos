package com.yichen.cosmos.cloud.platform.bean.dictionary;

/**
 * Created by Lizhengxian on 2017/3/28.
 * 工作职位
 */
public class JobPosition {
    private int id;
    private String resume;
    private String jobPositionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getJobPositionName() {
        return jobPositionName;
    }

    public void setJobPositionName(String jobPositionName) {
        this.jobPositionName = jobPositionName;
    }
}
