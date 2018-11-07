package com.example.demo.jinx.personal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("personal")
public class PersonalController {
	private static final Log logger = LogFactory
			.getLog(PersonalController.class);

	@RequestMapping("showPersonal")
	public String showPersonal() {
		return "personal";
	}
}
