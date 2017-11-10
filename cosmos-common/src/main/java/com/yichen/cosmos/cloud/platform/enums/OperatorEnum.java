package com.yichen.cosmos.cloud.platform.enums;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则操作符集合
 */
public enum OperatorEnum {

    EQUAL("=", "==", "等于，eg：A == B，即 A 等于 B；A，B可以是数值、字符串、布尔型、日期（遵循yyyy-MM-dd hh:mm:ss格式） 等"),
    GTHEN(">", ">", "大于，eg：A>B，即 A 大于 B； A，B 可以是数值、日期等比较值"),
    LTHEN("<", "<", "小于，eg：A<B，即 A 小于 B； A，B 可以是数值、日期等比较值"),
    GETHEN(">=", ">=", "大于等于，eg：A>=B，即 A 大于等于 B； A，B 可以是数值、日期等比较值"),
    LETHEN("<=", "<=", "小于等于，eg：A<=B，即 A 小于等于 B； A，B 可以是数值、日期等比较值"),
    NEQUAL("!=", "!=", "不等于，eg：A != B，即 A 不等于 B"),
    CONTAINS("包含", "contains", "包含，eg：A contains B； A，B可以是字符串 或者 A是列表 B是A中的一个元素"),
    NCONTAINS("不包含", "not contains", "不包含，eg：A not contains B； A，B可以是字符串 或者 A是列表 B是A中的一个元素"),
    MEMBEROF("包含于", "memberOf", "包含于， A memberOf B； A，B可以是字符串 或者 B是列表 A是B中的一个元素；B使用英文逗号分隔"),
    NMEMBEROF("不包含于", "not memberOf", "包含于， A not memberOf B； A，B可以是字符串 或者 B是列表 A是B中的一个元素；B使用英文逗号分隔"),
    MATCHES("正则匹配", "matches", "A 正则匹配 B， A matches B"),
    NMATCHES("不正则匹配", "not matches", "A 不正则匹配 B， A not matches B"),;

    private String fronDesc;
    private String backDesc;
    private String operatorDesc;

    OperatorEnum(String fronDesc, String backDesc, String operatorDesc) {
        this.fronDesc = fronDesc;
        this.backDesc = backDesc;
        this.operatorDesc = operatorDesc;
    }

    //判断judgeValue 是否为数组列表，通过操作符进行判断，对judgeVale进行约束处理
    //将judgeVale 处理成列表
    public static boolean isJudgeValueIsList(String operator) {
        if (operator.contains("memberOf")) {
            return true;
        }
        return false;
    }

    public String getFronDesc() {
        return fronDesc;
    }

    public void setFronDesc(String fronDesc) {
        this.fronDesc = fronDesc;
    }

    public String getBackDesc() {
        return backDesc;
    }

    public void setBackDesc(String backDesc) {
        this.backDesc = backDesc;
    }

    public String getOperatorDesc() {
        return operatorDesc;
    }

    public void setOperatorDesc(String operatorDesc) {
        this.operatorDesc = operatorDesc;
    }

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("thomas");
        nameList.add("jacky");
        nameList.add("tomy");

        System.out.println(nameList.toString());
        System.out.println(JSON.toJSONString(nameList));
    }
}
