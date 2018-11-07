package com.example.demo.jinx.Login;

public interface LoginService {

	public Page<Void> checkUserName(String userName);
	public Page<Void> login(LoginEntity entity);
}
