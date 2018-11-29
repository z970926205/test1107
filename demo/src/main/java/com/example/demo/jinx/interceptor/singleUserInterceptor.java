package com.example.demo.jinx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jinx.general.BaseController;
import com.example.demo.jinx.general.BaseEntity;
import com.example.demo.jinx.singleUser.SingleUserController;
import com.example.demo.jinx.singleUser.SingleUserEntity;

@Component
public class singleUserInterceptor implements HandlerInterceptor {
	private static final Log logger = LogFactory
			.getLog(singleUserInterceptor.class);
	@Autowired
	private SingleUserController singleUserController;
	@Autowired
	private BaseController baseController;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("");
		String registerDate = baseController.getSingleUserDate();
		int userId = baseController.getSessionUser().getId();
		if(registerDate != null){
			if(registerDate.equals(singleUserController.getSingleUser(userId))){
				return true;
			}else{
				baseController.DeleteSessionUser();
			}
		}
		logger.info("sessoin验证未通过");
		logger.info("验证不通过跳转至:" + request.getContextPath());
		response.sendRedirect(request.getContextPath() + "/");
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
