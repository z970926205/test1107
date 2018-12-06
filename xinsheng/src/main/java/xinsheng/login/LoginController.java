package xinsheng.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xinsheng.utils.JinxUtils;

@Controller
public class LoginController {
	private final Logger logger =Logger.getLogger(this.getClass());
	@Autowired
	private LoginService LoginService;
	
	@RequestMapping(value="/Login" ,method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<LoginEntity> insertUser(String token){
		logger.info("token:"+token);
		ResponseResult<LoginEntity> result = null;
		try {
			if(JinxUtils.checkStrings(token)){
				result = LoginService.login(token.trim());
			}else{
				new ResponseResult<String>(1,"Token is invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			new ResponseResult<String>(1,"SystemException");
		}
		return result;
	}
}
