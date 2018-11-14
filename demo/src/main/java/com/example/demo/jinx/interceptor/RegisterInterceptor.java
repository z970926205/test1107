package com.example.demo.jinx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RegisterInterceptor implements HandlerInterceptor{
	private static final Log logger = LogFactory.getLog(RegisterInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("t0919.interceptor.RegisterInterceptor.preHandle()");
		if(request.getSession().getAttribute("user")!=null){
			logger.info("sessoin验证通过");
			return true;
		}
		logger.info("sessoin验证未通过");
		response.sendRedirect(request.getContextPath());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
