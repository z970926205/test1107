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
	public BaseEntity getUser(BaseEntity entity) {
		logger.info("entity:"+entity);
		BaseEntity returnEntity = null;
		try {
			returnEntity = baseMapper.getUser(entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return returnEntity;
	}
	
}
