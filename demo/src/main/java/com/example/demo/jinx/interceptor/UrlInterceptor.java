package com.example.demo.jinx.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 0821.cj 控制台显示request请求路径及参数
 * 
 * @author Administrator
 *
 */
@Component
public class UrlInterceptor implements HandlerInterceptor {

	private static final Log logger = LogFactory.getLog(UrlInterceptor.class);
	
	private HttpSession nowSession;
	
	/**
	 * get current session
	 */
	public HttpSession getNowSession() {
		return nowSession;
	}

	public void setNowSession(HttpSession nowSession) {
		if(!nowSession.equals(this.nowSession)){
			this.nowSession = nowSession;
			if(this.nowSession != null){
				logger.info("The current session is different from the original session ");
			}
		}else{
			logger.info("The current session is the same as the original session");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		logger.info("start ");
		//Gets the current session
		setNowSession(request.getSession());
		logger.info("current sessionID:"+request.getSession().getId());
		logger.info(" Receiving a request:");
		String url = request.getRequestURL().toString();
		Enumeration pNames = request.getParameterNames();
		logger.info("url : " + url);
		logger.info("parameter  : ");
		StringBuffer properties = new StringBuffer();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter(name);
			properties.append(name + "=" + value + "&&");
		}
		logger.info(properties);
		logger.info("interrupt");
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}



}
