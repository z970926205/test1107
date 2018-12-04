package xinsheng.register;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xinsheng.utils.JinxUtils;

@Service
public class RegisterServiceImpl implements RegisterService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RegisterMapper registerMapper;
	@Autowired
	private JinxUtils utils;

	@Transactional
	public ResponseResult<String> insertUser(RegisterEntity user) {
		logger.info("user:" + user.getPhone());
		ResponseResult<String> responseResult = null;
		String token = null;
		registerMapper.setUser(user);
		logger.info("id:" + user.getId());
		if (user.getId() == null) {
			throw new RuntimeException();
		}
		token = utils.createJWT(user.getId() + "", user.getNickName(), null);
		logger.info("token:" + token);
		if (!utils.checkStrings(token)) {
			throw new RuntimeException();
		}
		if(registerMapper.setToken(token)!=1){
			throw new RuntimeException();
		}
		responseResult = new ResponseResult<String>(0, "success");
		responseResult.setData(token);
		return responseResult;
	}

	@Override
	public String test() {
		return "123123";
	}

}
