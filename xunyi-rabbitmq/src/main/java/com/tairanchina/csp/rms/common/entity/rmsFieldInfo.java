package com.tairanchina.csp.rms.common.entity;

public class rmsFieldInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String fieldCode;

    /**
     * 
     */
    private String fieldName;

    /**
     * 
     */
    private String factorCode;

    /**
     * 
     */
    private String factorName;

    /**
     * 
     */
    private String sourceName;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return field_code 
     */
    public String getFieldCode() {
        return fieldCode;
    }

    /**
     * 
     * @param fieldCode 
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    /**
     * 
     * @return field_name 
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 
     * @param fieldName 
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * 
     * @return factor_code 
     */
    public String getFactorCode() {
        return factorCode;
    }

    /**
     * 
     * @param factorCode 
     */
    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode == null ? null : factorCode.trim();
    }

    /**
     * 
     * @return factor_name 
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     * 
     * @param factorName 
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    /**
     * 
     * @return source_name 
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 
     * @param sourceName 
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }
}