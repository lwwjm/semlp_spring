package com.smelp.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smelp.pojo.DUser;
@Repository
public interface UserDao extends JpaRepository<DUser, String> {

}