package com.chenZ.dave.common.exception;

import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * Created by jianghengwei on 2017/04/12.
 * 参数校验合法性check 异常类
 */
@EqualsAndHashCode(callSuper = false)
public class ParamCheckException extends BssRuntimeException {


    /***
     * 异常构建
     *
     * @param message   异常消息
     */
    public ParamCheckException(String message) {
        this.errorCode = ErrorCodes.RPC_EXCEPTION.getCode();
        this.message = message;
    }

    /***
     * 异常构建
     *
     * @param message   异常消息
     */
    public ParamCheckException() {
        this.errorCode = ErrorCodes.RPC_EXCEPTION.getCode();
        this.message = ErrorCodes.RPC_EXCEPTION.getMessage();
    }


    /***
     * 异常构建
     *
     * @param errorCode 异常码
     * @param message   异常消息
     */
    public ParamCheckException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /***
     * 异常构建
     *
     * @param errorCode 异常码
     * @param message   异常消息
     */
    public ParamCheckException(int errorCode, String message, ErrorLevel errorLevel) {
        this.errorCode = errorCode;
        this.message = message;
        this.errorLevel = errorLevel;

    }

    /***
     * 异常构建,支持替换变量
     *
     * @param errorCode 异常码
     * @param message   异常消息
     */
    public ParamCheckException(int errorCode, String message, Object... arguments) {
        this.errorCode = errorCode;
        this.message = MessageFormat.format(message, arguments);
    }

    @Override
    public String getExceptionType() {
        return PARAM_CHECK_EXCEPTION;
    }
}
