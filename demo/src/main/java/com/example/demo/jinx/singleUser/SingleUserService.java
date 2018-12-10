package com.example.demo.jinx.singleUser;

public interface SingleUserService {
	public SingleUserEntity getSingleUser(SingleUserEntity entity);
	public SingleUserEntity setSingleUser(Integer id);
	public Integer DelSingleUser(String registerDate);
}
