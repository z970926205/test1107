package com.example.demo.jinx.personal;

public interface PersonalService {

	public Page<Void> updatePersonal(PersonalEntity entity);
	//修改头像页面
	public Page<Void> updatePersonalFace(PersonalEntity entity);

	public Page<Void> checkUserName(String userName);
}
