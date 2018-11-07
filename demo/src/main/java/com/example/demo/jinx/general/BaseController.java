package com.example.demo.jinx.general;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 通用
 * @author GP
 *
 */
@Controller
public class BaseController {
	private static final Log logger = LogFactory.getLog(BaseController.class);
	@Autowired
	private BaseService baseService;
	@Autowired
	HttpSession session;
	public boolean setSessionUser(Integer userId) {
		logger.info(userId);
		BaseEntity entity;
		try {
			entity = baseService.getUserById(userId);
			if(entity!=null){
				session.setAttribute("user", entity);
				logger.info(((BaseEntity)session.getAttribute("user")).toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public BaseEntity getSessionUser(Integer userId) {
		return null;
	}
	
	public boolean updateSessionUser(Integer userId) {
		return false;
	}
	public boolean DeleteSessionUser() {
		logger.info("");
		try {
			session.removeAttribute("user");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}
	
}
