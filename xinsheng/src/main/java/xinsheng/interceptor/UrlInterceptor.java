package xinsheng.interceptor;

import org.apache.log4j.Logger;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1203.cj 
 * 控制台显示request请求路径及参数
 * @author Administrator
 *
 */
public class UrlInterceptor implements HandlerInterceptor {

	protected final Logger logger =Logger.getLogger(this.getClass());

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

	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		logger.info(" ");
		logger.info(" 接收请求:");
		String url = request.getRequestURL().toString();
		/**
		 * request.getParameterNames()方法是将发送请求页面中form表单里所有具有name属性的表单对象获取(
		 * 包括button). 返回一个Enumeration类型的枚举.通过Enumeration的hasMoreElements()方法遍历.
		 * 再由nextElement()方法获得枚举的值.此时的值是form表单中所有控件的name属性的值.
		 * 最后通过request.getParameter()方法获取表单控件的value值.
		 */
		Enumeration pNames = request.getParameterNames();
		logger.info("url : " + url);
		logger.info("参数  : ");
		StringBuffer properties = new StringBuffer();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter(name);
			properties.append(name + "=" + value + "&&");
		}
		logger.info(properties);
		logger.info("中断");
		logger.info(" ");
		return true;
	}

}
