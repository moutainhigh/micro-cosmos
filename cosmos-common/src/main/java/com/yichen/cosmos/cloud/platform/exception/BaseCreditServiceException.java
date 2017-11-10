package com.yichen.cosmos.cloud.platform.exception;

import com.yichen.cosmos.cloud.platform.enums.CodeStatus;

public class BaseCreditServiceException extends RuntimeException {

    private static final long serialVersionUID = -3849156658140485253L;
    private boolean result;
    private String code;
    private String msg;

    public BaseCreditServiceException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void throwIPE(String paraError) {
        throw new BaseCreditServiceException(paraError, CodeStatus.INVALID_REQUEST_PARAMETER.getCode());
    }
}
