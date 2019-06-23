package com.smelp.core;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.smelp.pojo.DUser;
@Mapper
public interface UserDao extends JpaRepository<DUser, Long> ,JpaSpecificationExecutor{

}
