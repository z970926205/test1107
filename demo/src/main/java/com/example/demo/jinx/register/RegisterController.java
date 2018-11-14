package com.example.demo.jinx.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.jinx.general.Utils;

@Controller
@RequestMapping("Register")
public class RegisterController {
	private static final Log logger = LogFactory
			.getLog(RegisterController.class);
	@Autowired
	private RegisterService registerService;

	@RequestMapping("/showRegister")
	public String showRegister() {
		logger.info("showRegister()");
		return "register";
	}
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Page<Void> checkUserName(String userName) {
		Page<Void> page;
		try {
			page = registerService.checkUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
		}
		return page;
	}

	@RequestMapping("/register")
	@ResponseBody
	public Page<Void> register(String userName, String password) {
		logger.info("register()");
		RegisterEntity entity;
		Page<Void> page;
		try {
			if (Utils.checkStrings(userName,password)) {
				entity = new RegisterEntity();
				entity.setUserName(userName);
				entity.setPassword(password);
				entity.setCreator(userName);
				page = registerService.register(entity);
			}else{
				page = new Page<Void>(3, "输入的信息有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
		}
		return page;
	}
}
