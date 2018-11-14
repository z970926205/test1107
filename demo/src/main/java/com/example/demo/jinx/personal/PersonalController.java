package com.example.demo.jinx.personal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.jinx.general.BaseController;
import com.example.demo.jinx.general.Utils;

@Controller
@RequestMapping("personal")
public class PersonalController {
	private static final Log logger = LogFactory
			.getLog(PersonalController.class);
	@Autowired
	private PersonalService personalService;

	@RequestMapping("showPersonal")
	public String showPersonal() {
		return "personal";
	}

	@RequestMapping(value = "updatePersonal", method = RequestMethod.GET)
	public String personal() {
		return "updatePersonal";
	}

	@RequestMapping(value = "updatePersonal", method = RequestMethod.POST)
	@ResponseBody
	public Page<Void> updatePersonal(String id, String name, String sex,
			String phone, String email, String birthday) {
		logger.info("");
		Page<Void> page;
		PersonalEntity entity;
		try {
			if (Utils.checkStrings(name, sex, email, birthday,id, phone)) {
				entity = new PersonalEntity();
				entity.setId(Integer.parseInt(id.trim()));
				entity.setUserName(name);
				entity.setSex(sex);
				entity.setPhone(Integer.parseInt(phone.trim()));
				entity.setEmail(email);
				entity.setBirthday(birthday);
				page = personalService.updatePersonal(entity);
			} else {
				page = new Page<Void>(1, "参数异常");
				logger.info("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
			logger.info("error");
		}
		return page;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "checkUserName")
	@ResponseBody
	public Page<Void> checkUserName(String userName) {
		logger.info("");
		Page<Void> page;
		PersonalEntity entity;
		try {
			if (Utils.checkStrings(userName)) {
				page = personalService.checkUserName(userName);
			} else {
				page = new Page<Void>(1, "参数异常");
				logger.info("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
			logger.info("error");
		}
		return page;
	}
}
