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
public class RegisterInterceptor implements HandlerInterceptor {
	private static final Log logger = LogFactory
			.getLog(RegisterInterceptor.class);
	@Autowired
	private BaseController baseController;
	@Autowired
	private SingleUserController singleUserController;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("");
		BaseEntity baseEntity = baseController.getSessionUser();
		if (baseEntity != null) {
			logger.info("sessoin验证通过");
			logger.info("IP地址:"+request.getRemoteAddr());
			String registerDate = baseController.getSingleUserDate();
			if (registerDate != null) {
				if (registerDate.equals(singleUserController
						.getSingleUser(baseEntity.getId()))) {
					return true;
				} else {
					baseController.DeleteSessionUser();
					baseController.DelSingleUserDate();
					request.setAttribute("message", "账号被异地使用,请修改密码或重新登录!");
				}
			}
		}

		logger.info("sessoin验证未通过");
		logger.info("验证不通过转发至:" + request.getContextPath());
		request.getRequestDispatcher(request.getContextPath() + "/").forward(
				request, response);
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
