package com.example.demo.jinx.register;


public interface RegisterService {
	public Page<Void> checkUserName(String userName);
	public Page<Void> register(RegisterEntity entity);
}
