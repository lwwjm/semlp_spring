package com.smelp.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	
	Map<String,Object> findUser();

}
