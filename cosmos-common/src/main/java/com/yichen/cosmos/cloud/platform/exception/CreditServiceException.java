package com.yichen.cosmos.cloud.platform.exception;


import com.yichen.cosmos.cloud.platform.enums.CodeStatus;

public class CreditServiceException extends RuntimeException {

    private static final long serialVersionUID = -3849156658140485253L;
    private String code;

    public CreditServiceException() {
        this("调用失败", CodeStatus.CODE_SYS_ERROR.getCode());
    }

    public CreditServiceException(String code) {
        this("调用失败", code);
    }

    public CreditServiceException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public String getCode() {
        return code;
    }

    public static CreditServiceException internalErrorException() {
        return new CreditServiceException(CodeStatus.CODE_SYS_ERROR.getCode());
    }

    @Deprecated
    public static CreditServiceException httpErrorException() {
        return new CreditServiceException("调用失败: HTTP异常", CodeStatus.HTTP_ERROR.getCode());
    }

    public static CreditServiceException invalidParameterException(String paraError) {
        return new CreditServiceException(paraError, CodeStatus.INVALID_REQUEST_PARAMETER.getCode());
    }

    /*
     * throw InternalErrorException
     */
    public static void throwIEE() {
        throw new CreditServiceException(CodeStatus.CODE_SYS_ERROR.getCode());
    }

    /**
     * throw InvalidParameterException
     *
     * @param paraError a description of parameter error
     */
    public static void throwIPE(String paraError) {
        throw new CreditServiceException(paraError, CodeStatus.INVALID_REQUEST_PARAMETER.getCode());
    }

    public static void throwNull(String paraError) {
        throw new CreditServiceException(paraError, CodeStatus.CODE_NOT_NULL_ERROR.getCode());
    }

}
