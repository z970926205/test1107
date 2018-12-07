package com.example.demo.jinx.singleUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleUserMapper {
	public SingleUserEntity getSingleUser(Integer id);
	/**
	 * 存在则更新不存在则插入
	 * @param id
	 * @return
	 */
	public Integer setSingleUser(SingleUserEntity entity);
	public Integer DelSingleUser(String registerDate);
}
