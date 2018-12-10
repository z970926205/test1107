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
		try {
			BaseEntity baseEntity = baseController.getSessionUser();
			if (baseEntity != null) {
				logger.info("Session verification success");
				String registerDate = baseController.getSingleUserDate();
				if (registerDate != null) {
					logger.info("Database user login time:"+registerDate);
					logger.info("Database user login time:"+singleUserController
							.getSingleUser(baseEntity.getId()).getRegisterDate());
					if (registerDate.equals(singleUserController
							.getSingleUser(baseEntity.getId()).getRegisterDate())) {
						return true;
					} else {
						baseController.DeleteSessionUser();
						baseController.DelSingleUserDate();
						request.setAttribute("message", 
								"The account is used offsite, please change your password or log in again!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		logger.info("Session verification failed");
		logger.info("Verify not to forward to:" + request.getContextPath());
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
