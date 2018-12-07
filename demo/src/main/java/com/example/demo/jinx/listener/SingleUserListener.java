package com.example.demo.jinx.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.jinx.singleUser.SingleUserController;

@WebListener
public class SingleUserListener implements HttpSessionListener {
	private static final Log logger = LogFactory.getLog(SingleUserListener.class);
	
	@Autowired
	private SingleUserController singleUserController;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("sessionDestroyed Enter");
		try {
			HttpSession session = se.getSession();
			String userDate = (String)session.getAttribute("userDate");
			if(singleUserController.DelSingleUser(userDate) == 1){
				logger.info("Database login time deleted successfully");
			}else{
				logger.info("Database login time deletion failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		logger.info("sessionDestroyed End");
	}

}
