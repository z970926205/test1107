package com.example.demo.jinx.register;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
	public Integer insertUser(RegisterEntity entity);
	public RegisterEntity getUser(RegisterEntity entity);
}
