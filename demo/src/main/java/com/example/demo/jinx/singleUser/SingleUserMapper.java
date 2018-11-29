package com.example.demo.jinx.singleUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleUserMapper {
	public SingleUserEntity getSingleUser(Integer id);
	public Integer setSingleUser(Integer id);
}
