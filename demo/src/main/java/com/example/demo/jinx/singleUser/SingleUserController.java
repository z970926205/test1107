package com.example.demo.jinx.singleUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SingleUserController {
	private static final Log logger = LogFactory
			.getLog(SingleUserController.class);

	@Autowired
	private SingleUserService singleUserService;

	public SingleUserEntity getSingleUser(Integer userId) {
		logger.info("userId;" + userId);
		try {
			SingleUserEntity getSingleUserEntity = new SingleUserEntity();
			getSingleUserEntity.setUserId(userId);
			return singleUserService.getSingleUser(getSingleUserEntity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}

	/**
	 * 
	 * @param userId
	 * @return SingleUser
	 */
	public SingleUserEntity setSingleUser(Integer userId) {
		logger.info("userId:" + userId);
		try {
			if (userId != null) {
				return singleUserService.setSingleUser(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}

	public Integer DelSingleUser(String registerDate) {
		logger.info("registerDate:" + registerDate);
		try {
			return singleUserService.DelSingleUser(registerDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}
}
