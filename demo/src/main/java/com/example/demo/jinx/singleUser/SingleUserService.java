package com.example.demo.jinx.singleUser;

public interface SingleUserService {
	public SingleUserEntity getSingleUser(Integer id);
	public SingleUserEntity setSingleUser(Integer id);
	public Integer DelSingleUser(String registerDate);
}
