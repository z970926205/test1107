package com.example.demo.jinx.singleUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleUserMapper {
	public SingleUserEntity getSingleUser(SingleUserEntity entity);
	/**
	 * If the update does not exist, it will be inserted
	 * @param id
	 * @return
	 */
	public Integer setSingleUser(SingleUserEntity entity);
	public Integer DelSingleUser(String registerDate);
}
