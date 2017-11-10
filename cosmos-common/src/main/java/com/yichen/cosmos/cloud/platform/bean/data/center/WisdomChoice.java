package com.yichen.cosmos.cloud.platform.bean.data.center;

import java.util.List;

/**
 * Created by hzzt on 2017/9/12.
 * 用于查询枚举值返回用
 */
public class WisdomChoice {

    /**
     * 枚举字段id
     */
    private String choiceId;
    /**
     * 枚举值列表
     */
    private List<WisdomDictionary> choices;

    public WisdomChoice() {
    }

    public WisdomChoice(String choiceId, List<WisdomDictionary> choices) {
        this.choiceId = choiceId;
        this.choices = choices;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public List<WisdomDictionary> getChoices() {
        return choices;
    }

    public void setChoices(List<WisdomDictionary> choices) {
        this.choices = choices;
    }
}
