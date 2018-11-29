package com.example.demo.jinx.singleUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SingleUserController {
	private static final Log logger = LogFactory.getLog(SingleUserController.class);
	
	@Autowired
	private SingleUserService singleUserService;
	
	public SingleUserEntity getSingleUser(Integer id){
		logger.info("id;"+id);
		try {
			return singleUserService.getSingleUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}
	
	public Integer setSingleUser(Integer id) {
		try {
			return singleUserService.setSingleUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
