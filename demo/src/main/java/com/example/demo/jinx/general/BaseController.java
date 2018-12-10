package com.example.demo.jinx.general;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.jinx.interceptor.UrlInterceptor;

/**
 * common
 * 
 * @author GP
 *
 */
@Controller
public class BaseController {
	// private static final Log logger = LogFactory.getLog(.class);
	private static final Log logger = LogFactory.getLog(BaseController.class);
	@Autowired
	private BaseService baseService;
	@Autowired
	private UrlInterceptor urlInterceptor;

	public boolean setSessionUser(Integer userId) {
		logger.info(userId);
		HttpSession session = urlInterceptor.getNowSession();
		BaseEntity entity;
		BaseEntity returnEntity;
		try {
			entity = new BaseEntity();
			entity.setId(userId);
			returnEntity = baseService.getUser(entity);
			if (returnEntity != null) {
				logger.info("sessionID:"+session.getId());
				session.setAttribute("user", returnEntity);
				logger.info("session:"
						+ ((BaseEntity) session.getAttribute("user"))
								.toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public BaseEntity getSessionUser() {
		logger.info("");
		HttpSession session = urlInterceptor.getNowSession();
		logger.info("sessionID:"+session.getId());
		return (BaseEntity)session.getAttribute("user");
	}

	public boolean updateSessionUser(Integer userId) {
		logger.info(userId);
		return setSessionUser(userId);
	}

	public boolean DeleteSessionUser() {
		logger.info("");
		HttpSession session = urlInterceptor.getNowSession();
		logger.info("sessionID:"+session.getId());
		try {
			session.removeAttribute("user");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public boolean checkUserName(String userName) {
		BaseEntity entity;
		try {
			entity = new BaseEntity();
			entity.setUserName(userName);
			if (baseService.getUser(entity) != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public boolean setSingleUserDate(String singleUserDate) {
		logger.info("singleUserDate:" + singleUserDate);
		try {
			HttpSession session = urlInterceptor.getNowSession();
			logger.info("sessionID:"+session.getId());
			session.setAttribute("userDate", singleUserDate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}
	public boolean DelSingleUserDate() {
		logger.info("");
		try {
			HttpSession session = urlInterceptor.getNowSession();
			logger.info("sessionID:"+session.getId());
			session.removeAttribute("userDate");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return false;
	}
	public String getSingleUserDate() {
		logger.info("");
		String registerDate;
		try {
			HttpSession session = urlInterceptor.getNowSession();
			logger.info("sessionID:"+session.getId());
			registerDate = (String)session.getAttribute("userDate");
			return registerDate;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}
}
