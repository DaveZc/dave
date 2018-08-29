package com.chenZ.dave.common.aop;

import com.chenZ.dave.common.ResultModel;
import com.chenZ.dave.common.exception.BizException;
import com.chenZ.dave.common.exception.ParamCheckException;
import com.chenZ.dave.util.tools.TraceIDGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;

/**
 * Service拦截, 主要完成:
 * 1. 异常处理和异常返回值封装
 * 2. 日志记录
 *
 * @author 蒋恒伟
 * @author shenjianping
 * @data 2017-08-23
 * <p>
 * 统一处理ParamCheckException
 * 返回ResultModel对象输出
 * @date 2017-10-07
 */
@Component
@Aspect
public class ServiceAspect {

    /**
     * 普通日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    public static final String MDC_TRADE_ID = "INNER_TRACE_ID";

    @Around("@annotation(com.caocao.bss.ump.common.aop.CResponseBody)" +
            " || @annotation(com.caocao.bss.ump.common.aop.LogAnnotation)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        Object obj = null;
        long startTime = System.currentTimeMillis();

        try {

            MDC.put(MDC_TRADE_ID, TraceIDGenerator.generate());
            obj = joinPoint.proceed();

        } catch (Throwable ex) {

            obj = parserResultException(joinPoint, ex);

        } finally {

            long costTime = System.currentTimeMillis() - startTime;

            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

            String argsStr = formatParameters(joinPoint, method);

            String strBuf = new StringBuilder(method.getDeclaringClass().getName()).append(" - ").append(method.getName())
                    .append(" - request = {").append(argsStr).append("}").toString();

            logger.info("{} - result = {} - cost = {} ms", strBuf, obj, costTime);

            MDC.remove(MDC_TRADE_ID);

        }
        return obj;
    }

    private Object parserResultException(ProceedingJoinPoint joinPoint, Throwable ex) {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String argsStr = formatParameters(joinPoint, method);
        String message = "exception:" + ex.getClass().getSimpleName() + "\n Method:" + method.toString() + "\n " +
                "Arguments:{\n" + argsStr + "}";

        if (ex instanceof ParamCheckException) {
            ParamCheckException pex = (ParamCheckException) ex;

            //根据不同异常子类打印不同的日志级别
			logger.info(message, ex);

            ResultModel<Boolean> result = new ResultModel<>();

            result.setCode(pex.getErrorCode());
            result.setMessage(pex.getMessage());

            return result;

        } else if (ex instanceof BizException) {

            BizException bizException = (BizException) ex;

			logger.info(message, ex);

			ResultModel<Boolean> result = new ResultModel<>();

			result.setCode(bizException.getErrorCode());
			result.setMessage(bizException.getMessage());

			return result;

        }

        logger.warn(message, ex);

		ResultModel<Boolean> result = new ResultModel<>();
		result.setCode(-1);
		result.setMessage("系统处理异常，请联系管理员");
		return result;
    }

    private String formatParameters(ProceedingJoinPoint pig, Method method) {

        Object[] args = pig.getArgs();

        Class<?>[] paramNames = method.getParameterTypes();

        StringBuilder argStr = new StringBuilder();
        for (int i = 0; i < args.length; i++) {

            //如果是文件对象，则不需要转换成json对象
            if (args[i] instanceof MultipartFile) {
                argStr.append(paramNames[i]).append(":").append(args[i].toString()).append("\n");
            } else {
                argStr.append(paramNames[i]).append(":").append(args[i]).append("\n");
            }

        }

        return argStr.toString();
    }
}
