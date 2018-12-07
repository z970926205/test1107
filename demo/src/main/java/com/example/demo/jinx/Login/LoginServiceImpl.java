package com.example.demo.jinx.Login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jinx.general.BaseController;
import com.example.demo.jinx.general.Utils;
import com.example.demo.jinx.singleUser.SingleUserController;
import com.example.demo.jinx.singleUser.SingleUserEntity;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Log logger = LogFactory.getLog(LoginServiceImpl.class);
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private BaseController baseController;
	@Autowired
	private SingleUserController singleUserController;

	@Override
	public Page<Void> checkUserName(String userName) {
		logger.info("userName:" + userName);
		Page<Void> page;
		LoginEntity entity;
		LoginEntity returnEntity;
		try {
			if (!Utils.checkStrings(userName)) {
				page = new Page<Void>(2, "用户名输入有误");
			} else {
				entity = new LoginEntity();
				entity.setUserName(userName);
				returnEntity = loginMapper.getUser(entity);
				if (returnEntity != null) {
					page = new Page<Void>(0, "用户名存在");
				} else {
					page = new Page<Void>(1, "用户名不存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
		}
		return page;
	}
	
	@Override
	public Page<Void> login(LoginEntity entity) {
		logger.info("entity:" + entity.toString());
		Page<Void> page;
		LoginEntity returnEntity;
		SingleUserEntity singleUserEntity;
		String message = null;
		Integer flag = 1;
		try {
			returnEntity = loginMapper.getUser(entity);
			if (returnEntity != null) {
				// Password encryption
				if ((returnEntity.getPassword()).equals(Utils.stringMD5(entity
						.getPassword()))) {
					// Set login time to prevent double login
					singleUserEntity = singleUserController
							.setSingleUser(returnEntity.getId());
					if (singleUserEntity != null) {
						String registerDate = singleUserEntity
								.getRegisterDate();
						if (baseController.setSessionUser(returnEntity.getId())
								&& baseController
										.setSingleUserDate(registerDate)) {
							flag = 0;
							message = "suesses";
						} else {
							message = "Session setting login user exception";
						}
					} else {
						message = "Set logon information exception";
					}
				} else {
					message = "wrong password";
				}
			} else {
				message = "Username does not exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(message);
		page = new Page<Void>(flag, message);
		return page;
	}

}
