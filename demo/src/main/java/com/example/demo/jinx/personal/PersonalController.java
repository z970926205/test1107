package com.example.demo.jinx.personal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 显示修改头像页面
	@RequestMapping(value = "updatePersonalFace", method = RequestMethod.GET)
	public String PersonalFace() {
		return "updatePersonalFace";
	}

	// 显示修改密码页面
	@RequestMapping(value = "updatePersonalPassword", method = RequestMethod.GET)
	public String PersonalPassword() {
		return "updatePersonalPassword";
	}

	// 修改头像页面
	@RequestMapping(value = "updatePersonalFace", method = RequestMethod.POST)
	@ResponseBody
	public Page<Void> updatePersonalFace(String userFace, Integer id) {
		logger.info("userFace:" + userFace + "id:" + id);
		Page<Void> page;
		PersonalEntity entity;
		try {
			if (Utils.isNulls(id) && Utils.checkStrings(userFace)) {
				entity = new PersonalEntity();
				entity.setId(id);
				entity.setUserImage(userFace);
				page = personalService.updatePersonalFace(entity);
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

	@RequestMapping(value = "updatePersonal", method = RequestMethod.POST)
	@ResponseBody
	public Page<Void> updatePersonal(String id, String userName, String sex,
			String phone, String email, String birthday) {
		logger.info(id + "/" + userName + "/" + sex + "/" + phone + "/" + email
				+ "/" + birthday);
		Page<Void> page;
		PersonalEntity entity;
		try {
			if (Utils.checkStrings(userName, sex, email, birthday, id, phone)) {
				entity = new PersonalEntity();
				entity.setId(Integer.parseInt(id));
				entity.setUserName(userName);
				entity.setSex(sex);
				entity.setPhone(phone);
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
