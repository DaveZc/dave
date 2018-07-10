package com.chenZ.dave.common.exception;

/**
 *
 * @author chen.z
 * @date 2018/7/10
 */
public class ErrorCode {

    private int code;
    private String message;

    public ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
