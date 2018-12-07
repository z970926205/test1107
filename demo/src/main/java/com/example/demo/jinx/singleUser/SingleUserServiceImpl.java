package com.example.demo.jinx.singleUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SingleUserServiceImpl implements SingleUserService{
	private static final Log logger = LogFactory.getLog(SingleUserServiceImpl.class);
	
	@Autowired
	private SingleUserMapper singleUserMapper;
	@Override
	public SingleUserEntity getSingleUser(Integer id) {
		logger.info("id:"+id);
		try {
			return singleUserMapper.getSingleUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}
	@Override
	public SingleUserEntity setSingleUser(Integer userId) {
		SingleUserEntity singleUser = null;
		SingleUserEntity returnEntity = null;
		try {
			singleUser = new SingleUserEntity();
			singleUser.setUserId(userId);
			singleUserMapper.setSingleUser(singleUser);
			returnEntity = singleUserMapper.getSingleUser(singleUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return returnEntity;
	}
	@Override
	public Integer DelSingleUser(String registerDate) {
		try {
			return singleUserMapper.DelSingleUser(registerDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
