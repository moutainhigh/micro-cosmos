package com.yichen.cosmos.cloud.platform.bean.data.center;

import java.io.Serializable;

/**
 * Created by thomas.su on 2017/5/5.
 */
public class WisdomDictionary implements Serializable {

    /**
     * 类别id，关联field字段表
     */
    private String choiceId;
    /**
     * 英文简称
     */
    private String resume;
    /**
     * 中文名称
     */
    private String choiceName;

    /**
     * 值
     */
    private String choiceValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1:有效 0:无效
     */
    private String flag;
    private String createTime;
    private String updateTime;


    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
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
