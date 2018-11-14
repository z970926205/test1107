package com.example.demo.jinx.personal;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalMapper {

	public Integer updateUser(PersonalEntity entity);
}
