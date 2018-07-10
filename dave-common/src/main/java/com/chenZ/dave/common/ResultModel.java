//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chenZ.dave.common;

import com.chenZ.dave.common.exception.ErrorCode;

import java.text.MessageFormat;

public class ResultModel<T> {
    private static final int REST_SUCCESS_CODE = 200;
    private String message = "success";
    private int code = 200;
    private T data;

    public ResultModel() {
    }

    public ResultModel(T data) {
        this.data = data;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(ErrorCode error, Object... arguments) {
        this.code = error.getCode();
        this.message = MessageFormat.format(error.getMessage(), arguments);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(ErrorCode error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public void setError(ErrorCode error, Object... arguments) {
        this.code = error.getCode();
        this.message = MessageFormat.format(error.getMessage(), arguments);
    }

    public boolean isSuccess() {
        return 200 == this.code;
    }

    @Override
    public String toString() {
        return "ResultModel{message=\'" + this.message + '\'' + ", code=" + this.code + ", data=" +this.data + '}';
    }
}
