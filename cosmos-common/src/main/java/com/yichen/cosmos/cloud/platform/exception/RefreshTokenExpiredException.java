package com.yichen.cosmos.cloud.platform.exception;

/**
 * Created by Lizhengxian on 2017/6/26.
 */
public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
