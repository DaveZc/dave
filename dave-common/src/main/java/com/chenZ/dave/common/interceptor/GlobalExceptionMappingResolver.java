package com.chenZ.dave.common.interceptor;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.chenZ.dave.common.ResultModel;
import com.chenZ.dave.common.exception.BizException;
import com.chenZ.dave.common.exception.BssRuntimeException;
import com.chenZ.dave.common.exception.ErrorCodes;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tian.feng1 on 2017/8/24.
 */
@Data
public class GlobalExceptionMappingResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionMappingResolver.class);

    private String  charset;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        BssRuntimeException bssRuntimeException = null;

        logger.error("error info-->{}:",ExceptionUtils.getStackTrace(ex));

        response.setCharacterEncoding(StringUtils.isEmpty(charset)?"utf-8":charset);

        if (ex instanceof BssRuntimeException) {
            bssRuntimeException = (BssRuntimeException) ex;
            //dubbo异常转化
        }else if (ex instanceof RpcException){
            bssRuntimeException = new BizException(ErrorCodes.RPC_EXCEPTION);
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            bssRuntimeException = new BizException(ErrorCodes.RPC_EXCEPTION);
        }

        //视图信息
        String viewName = determineViewName(ex, request);
        ModelAndView mv = null;

        //视图信息 页面信息
        if (StringUtils.isNotBlank(viewName) && isPage(request)) {
            mv = getModelAndView(viewName, ex, request);
            mv.addObject("code", bssRuntimeException.getErrorCode());
            mv.addObject("message", bssRuntimeException.getMessage());
            return mv;
        }

        PrintWriter writer = null;
        try {
            // json 请求返回-----返回格式开放出来配置?
            writer = response.getWriter();
            writer.write(JSON.toJSONString(new ResultModel(bssRuntimeException.getErrorCode(), bssRuntimeException.getMessage())));
            writer.flush();
        } catch (IOException e) {
            logger.error("deal error reason:", (null == ex.getCause() ? ex.getMessage() : ex.getCause().getMessage()));
            logger.error("deal error stackTrace", ExceptionUtils.getStackTrace(ex));
        } finally {
            if (null != writer) writer.close();
        }
        return new ModelAndView();
    }


    /**
     * 是否来自页面
     *
     * @param request
     * @return
     */
    private boolean isPage(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        return !(accept != null && !(accept.indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)));
    }
}
