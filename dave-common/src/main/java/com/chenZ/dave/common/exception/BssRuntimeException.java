package com.chenZ.dave.common.exception;

import lombok.Data;

/**
 * @Author : jianghengwei
 * @Date :Created by 2017/8/25 下午5:31.
 * @Description :
 */
@Data
public abstract class BssRuntimeException extends RuntimeException {

	public static final String PARAM_CHECK_EXCEPTION = "ParamCheckException";
	public static final String BIZ_EXCEPTION = "BizException";


	public BssRuntimeException(){}


	protected String message;

	protected int errorCode;

	protected ErrorLevel errorLevel = ErrorLevel.INFO;

	public abstract String getExceptionType();
}
