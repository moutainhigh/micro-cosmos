package com.xunyi.cloud.wisdom.activiti.model;

import lombok.Data;

@Data
public class RmsFactorInfo {
    private Integer id;

    /**
     * 因子编码
     */
    private String factorCode;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 数据源编码
     */
    private String sourceCode;

    /**
     * 数据源名称
     */
    private String sourceName;


}