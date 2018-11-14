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
	//private static final Log logger = LogFactory.getLog(.class);
	private static final Log logger = LogFactory.getLog(BaseController.class);
	@Autowired
	private BaseService baseService;
	@Autowired
	HttpSession session;
	public boolean setSessionUser(Integer userId) {
		logger.info(userId);
		BaseEntity entity;
		BaseEntity returnEntity;
		try {
			entity = new BaseEntity();
			entity.setId(userId);
			returnEntity = baseService.getUser(entity);
			if(returnEntity!=null){
				session.setAttribute("user", returnEntity);
				logger.info(((BaseEntity)session.getAttribute("user")).toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public BaseEntity getSessionUser() {
		BaseEntity entity;
		try {
			entity = (BaseEntity)session.getAttribute("user");
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
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
	
	public boolean checkUserName(String userName) {
		BaseEntity entity;
		try {
			entity = new BaseEntity();
			entity.setUserName(userName);
			if(baseService.getUser(entity) !=null)
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}
	
}
