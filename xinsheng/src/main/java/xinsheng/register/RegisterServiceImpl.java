package xinsheng.register;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xinsheng.utils.JinxUtils;

@Service
public class RegisterServiceImpl implements RegisterService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RegisterMapper registerMapper;
	
	public ResponseResult<String> insertUser(RegisterEntity user) {
		logger.info("user:" + user.getPhone());
		ResponseResult<String> responseResult = null;
		String token = null;
		try {
			registerMapper.setUser(user);
			logger.info("id:"+user.getId());
			token = JinxUtils.createJWT(user.getId()+"", user.getNickName(), null);
			logger.info("token:"+token);
			responseResult = new ResponseResult<String>(0, "success");
			responseResult.setData(token);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			new ResponseResult<RegisterEntity>(1, "ϵͳ�쳣");
		}
		return responseResult;
	}

}
