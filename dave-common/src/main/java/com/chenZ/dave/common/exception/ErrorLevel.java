package com.chenZ.dave.common.exception;

/**
 * @Author : jianghengwei
 * @Date :Created by 2017/4/12 上午11:19.
 * @Description :异常类型定义
 */
public enum ErrorLevel {
	DEBUG(1),
	INFO(2),
	WARN(3),
	ERROR(4);

	private final int value;

	ErrorLevel(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static ErrorLevel findByValue(int value) {
		switch(value) {
			case 1:
				return DEBUG;
			case 2:
				return INFO;
			case 3:
				return WARN;
			case 4:
				return ERROR;
			default:
				return null;
		}
	}
}

