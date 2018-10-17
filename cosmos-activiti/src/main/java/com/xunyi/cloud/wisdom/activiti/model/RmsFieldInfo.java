package com.xunyi.cloud.wisdom.activiti.model;

import lombok.Data;

@Data
public class RmsFieldInfo {
    private Integer id;
    private String fieldCode;
    private String fieldName;

    private String factorCode;
    private String factorName;
    private String sourceName;

}