package com.example.demo.jinx.singleUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

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
	public Integer setSingleUser(Integer id) {
		try {
			return singleUserMapper.setSingleUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
