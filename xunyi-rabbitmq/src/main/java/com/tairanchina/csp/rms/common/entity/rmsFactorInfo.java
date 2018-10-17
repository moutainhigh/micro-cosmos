package com.tairanchina.csp.rms.common.entity;

public class rmsFactorInfo {
    /**
     * 
     */
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
     * 因子编码
     * @return factor_code 因子编码
     */
    public String getFactorCode() {
        return factorCode;
    }

    /**
     * 因子编码
     * @param factorCode 因子编码
     */
    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode == null ? null : factorCode.trim();
    }

    /**
     * 因子名称
     * @return factor_name 因子名称
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     * 因子名称
     * @param factorName 因子名称
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    /**
     * 数据源编码
     * @return source_code 数据源编码
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * 数据源编码
     * @param sourceCode 数据源编码
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }

    /**
     * 数据源名称
     * @return source_name 数据源名称
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 数据源名称
     * @param sourceName 数据源名称
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }
}