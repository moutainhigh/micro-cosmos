package com.xunyi.cloud.wisdom.activiti.service;

/**
 * @Author:thomas su
 * @Date: 2018/8/8 11:21
 * @Description:
 */
public interface IRegularService {
    public boolean match(String A, String B, boolean isFormula);

    /**
     * 是否为数字
     * @param number
     * @return
     */
    public boolean isNumber(String number);

}
