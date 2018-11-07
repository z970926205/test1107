package com.example.demo.jinx.Login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	public LoginEntity getUser(LoginEntity entity);
}
