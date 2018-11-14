package com.example.demo.jinx.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.jinx.general.BaseController;

@Controller
public class MainController {
	private static final Log logger = LogFactory.getLog(MainController.class);
	@Autowired
	private BaseController baseController;
	
	@RequestMapping("/")
	public String showMain(){
		return "index";
	}
	
	@RequestMapping("/1")
	public String s(){
		return "Bootstrap";
	}
	
	@RequestMapping("/exitUser")
	public String exitUser(){
		try {
			if(!baseController.DeleteSessionUser()){
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
			return "error";
		}
		return "index";
	}
	
}
