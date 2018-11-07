package com.example.demo.jinx.general;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author GP
 *
 */
@Service
public class BaseServiceImpl implements BaseService{
	private static final Log logger = LogFactory.getLog(BaseServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;

	@Override
	public BaseEntity getUserById(Integer id) {
		logger.info(id);
		BaseEntity entity = null;
		try {
			entity = baseMapper.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return entity;
	}
}
