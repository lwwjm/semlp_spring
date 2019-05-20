package com.smelp.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smelp.mapper.UserDao;
import com.smelp.pojo.DUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmelpSpringApplicationTests {
	@Autowired
	private  UserDao  userDao;

	@Test
	public void contextLoads() {
		   DUser userDO = new DUser();
		  
	        
	        userDao.save(userDO);
		
		
	}

}
