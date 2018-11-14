package com.example.demo.jinx.personal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jinx.general.BaseController;
import com.example.demo.jinx.general.BaseEntity;
@Service
public class PersonalServiceImpl implements PersonalService{
	private static final Log logger = LogFactory
			.getLog(PersonalServiceImpl.class);
	@Autowired
	private PersonalMapper personalMapper;
	@Autowired
	private BaseController baseController;

	@Override
	public Page<Void> updatePersonal(PersonalEntity entity) {
		logger.info("entity:" + entity.toString());
		Page<Void> page;
		try {
			if(personalMapper.updateUser(entity) == 1){
				if(baseController.updateSessionUser(entity.getId())){
					page = new Page<Void>(0, "success");
				}else{
					page = new Page<Void>(1, "session更新用户异常");
				}
			}else{
				page = new Page<Void>(2, "sql异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
			logger.info("error");
		}
		return page;
	}

	@Override
	public Page<Void> checkUserName(String userName) {
		logger.info("userName:" + userName);
		Page<Void> page;
		BaseEntity baseEntity;
		try {
			if(!baseController.checkUserName(userName)){
				page = new Page<Void>(0, "用户名可用");
			}else{
				baseEntity = baseController.getSessionUser();
				if(baseEntity ==null){
					page = new Page<Void>(1, "请重新登陆");
				}else{
					//判断是否等于本身
					if((baseEntity.getUserName()).equals(userName)){
						page = new Page<Void>(0, "用户名可用");
					}else{
						page = new Page<Void>(1, "用户名已存在");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
			logger.info("error");
		}
		return page;
	}

}
