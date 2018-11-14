package com.example.demo.jinx.Login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jinx.general.BaseController;
import com.example.demo.jinx.general.Utils;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Log logger = LogFactory.getLog(LoginServiceImpl.class);
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private BaseController baseController;

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
		try {
			returnEntity = loginMapper.getUser(entity);
			if (returnEntity != null) {
				//密码加密
				if ((returnEntity.getPassword()).equals(
						Utils.stringMD5(entity.getPassword()))) {
					if(baseController.setSessionUser(returnEntity.getId())){
						page = new Page<Void>(0, "登陆成功");
					}else{
						logger.info("session设置登录用户异常");
						throw new RuntimeException();
					}
				} else {
					page = new Page<Void>(1, "密码错误");
				}
			} else {
				page = new Page<Void>(1, "用户名不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
			logger.info("error");
		}
		return page;
	}

}
