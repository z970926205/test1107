package com.example.demo.jinx.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jinx.general.Utils;

@Service
public class RegisterServiceImpl implements RegisterService {
	private static final Log logger = LogFactory.getLog(RegisterServiceImpl.class);
	@Autowired
	private RegisterMapper registerMapper;

	@Override
	public Page<Void> register(RegisterEntity entity) {
		logger.info("entity:" + entity.toString());
		Page<Void> page;
		try {
			entity.setPassword(Utils.stringMD5(entity.getPassword()));
			if(registerMapper.insertUser(entity)==1){
				page = new Page<Void>(0, "注册成功");
			}else{
				page = new Page<Void>(1, "注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(2, "系统异常");
		}
		return page;
	}
	
	@Override
	public Page<Void> checkUserName(String userName) {
		logger.info("userName:" + userName);
		Page<Void> page;
		RegisterEntity entity;
		RegisterEntity returnEntity;
		try {
			if (!Utils.checkString(userName)) {
				page = new Page<Void>(2, "用户名输入有误");
			} else {
				entity = new RegisterEntity();
				entity.setUserName(userName);
				returnEntity = registerMapper.getUser(entity);
				if (returnEntity == null) {
					page = new Page<Void>(0, "用户名可用");
				} else {
					page = new Page<Void>(1, "用户名已存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
		}
		return page;
	}

}
