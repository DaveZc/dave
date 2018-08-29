package com.chenZ.dave.common.aop;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * Created by 老蒋 on 2017/08/23.
 * 用于标记对外暴露的api service的方法定义
 * 主要用于serviceAspect，记录api操作参数，耗时等日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@LogAnnotation
@ResponseBody
public @interface CResponseBody {
	/**false:接口aop拦截不打印body日志*/
	boolean value() default true;
}
