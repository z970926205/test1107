package com.example.demo.jinx.Login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.jinx.general.Utils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("Login")
public class loginController {
	private static final Log logger = LogFactory.getLog(loginController.class);
	@Autowired
	private Producer producer;
	@Autowired
	private LoginService loginService;

	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("captcha.jpg")
	@ResponseBody
	public void captcha(HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		logger.info("captcha:" + text);
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	@RequestMapping("/checkUserName")
	@ResponseBody
	public Page<Void> checkUserName(String userName) {
		Page<Void> page;
		try {
			page = loginService.checkUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
		}
		return page;
	}

	@RequestMapping("/login")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<Void> login(String userName, String password, String captcha,
			HttpSession session) {
		Page<Void> page;
		LoginEntity entity;
		try {
			if (Utils.checkStrings(userName,password,captcha)) {
				String kaptcha = (String) session
						.getAttribute(Constants.KAPTCHA_SESSION_KEY);
				if (captcha.equalsIgnoreCase(kaptcha)) {
					entity = new LoginEntity();
					entity.setUserName(userName);
					entity.setPassword(password);
					page = loginService.login(entity);
				} else {
					page = new Page(2, "验证码错误");
				}
			} else {
				page = new Page(3, "输入的信息有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(1, "系统异常");
		}
		return page;
	}
}
