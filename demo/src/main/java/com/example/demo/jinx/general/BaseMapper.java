package com.example.demo.jinx.general;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper {

	public BaseEntity getUser(BaseEntity entity);
}
