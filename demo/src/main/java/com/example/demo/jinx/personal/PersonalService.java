package com.example.demo.jinx.personal;

public interface PersonalService {

	public Page<Void> updatePersonal(PersonalEntity entity);

	public Page<Void> checkUserName(String userName);
}
