package com.yichen.cosmos.cloud.platform.enums;

public enum CodeStatus {
    //基础类
    CODE_SUCCESS("请求成功！", "10100"),
    CODE_ERROR("请求失败！", "10101"),
    CODE_TOKEN_ERROR("请求未授权！", "10102"),
    CODE_TOKEN_TIMEOUT_ERROR("请求TOKEN已失效！", "10103"),
    CODE_SESSION_TIMEOUT_ERROR("爬虫会话超时", "10104"),
    //认证类
    CODE_AUTHENTI_SUCCESS("认证成功！", "10200"),
    CODE_AUTHENTI_ERROR("认证失败！", "10201"),
    //权限类
    CODE_AUTH_ERROR("用户未授权！", "10301"),
    CODE_NOT_PERMISSION("无权限访问！", "10302"),
    //查找类
    INVALID_REQUEST_PARAMETER("无效的请求参数！", "10401"),
    CODE_NOT_NULL_ERROR("参数不能为空或参数错误！", "10402"),
    PARAMETER_MISMATCH("参数不匹配！", "10403"),
    CODE_NOT_FOUND("查询资源不存在！", "10404"),
    CODE_UNABLE_TO_SERVICE("无法连接服务器！", "10405"),
    //异常错误类
    HTTP_ERROR("HTTP错误！", "10501"),
    HTTP_OVERTIME_ERROR("HTTP请求超时！", "10502"),
    REQUEST_UNDER_PROCESSING("请求在处理！", "10504"),
    CODE_SYS_ERROR("系统内部错误！", "10505"),
    //操作类
    OPERATE_CODE_SUCCESS("操作成功！", "10100"),
    OPERATE_CODE_ERROR("操作失败！", "10101"),
    //报告类
    REPORT_CODE_SUCCESS("成功", "200"),
    REPORT_CODE_ERROR("失败", "400"),;

    // 成员变量
    private String msg;
    private String code;

    // 构造方法
    private CodeStatus(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    // 普通方法
    public static String getName(String code) {
        for (CodeStatus c : CodeStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.msg;
            }
        }
        return "";
    }

    // get set 方法
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}