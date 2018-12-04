package xinsheng.register;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xinsheng.utils.JinxUtils;

@Controller
public class RegisterController {
	private final Logger logger =Logger.getLogger(this.getClass());
	@Autowired
	private RegisterService registerService;
	@Value("${tokenSecret}")
	private String te11;
	
	@RequestMapping(value="/register" ,method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> insertUser(String phone){
		logger.info("phone:"+phone);
		ResponseResult<String> result = null;
		RegisterEntity entity;
		try {
			if(!JinxUtils.checkStrings(phone)){
				new ResponseResult<String>(1,"无效手机号");
			}
			entity = new RegisterEntity();
			entity.setPhone(phone);
			result = registerService.insertUser(entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			new ResponseResult<String>(1,"SystemException");
		}
		return result;
	}
	
	@RequestMapping(value="/" ,method=RequestMethod.GET)
	@ResponseBody
	public String test(){
		return te11;
	}
}
