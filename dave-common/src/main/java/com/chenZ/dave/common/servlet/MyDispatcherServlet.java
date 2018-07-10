package com.chenZ.dave.common.servlet;

import com.chenZ.dave.util.tools.TraceIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : jianghengwei
 * @Date :Created by 2017/8/25 下午2:01.
 * @Description :
 */
@Slf4j
public class MyDispatcherServlet extends DispatcherServlet {

	public static final String MDC_TRADE_ID = "INNER_TRACE_ID";

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			MDC.put(MDC_TRADE_ID, TraceIDGenerator.generate());
			log.info(request.getRequestURI());
			super.doDispatch(request, response);

		} finally {
			MDC.remove(MDC_TRADE_ID);
		}
	}

}
