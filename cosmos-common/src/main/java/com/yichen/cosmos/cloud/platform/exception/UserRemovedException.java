package com.yichen.cosmos.cloud.platform.exception;

/**
 * Created by Lizhengxian on 2017/6/26.
 */
public class UserRemovedException extends Exception {

    public UserRemovedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRemovedException(String message) {
        super(message);
    }
}
