package com.smelp.service.impl;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smelp.core.UserDao;
import com.smelp.pojo.DUser;
import com.smelp.service.EventLoopCallback;
@Service
public class EventLoopCallbackImpl implements EventLoopCallback{

	@Autowired
	private  UserDao uerDao;
	
	
	@Override
	public Callable<String> callback(String str) {
		return new Callable<String>() {

			@Override
			public String call()  {
				try {
					DUser  user=new DUser();
					user.setId("1231231");
					user.setAccount("1231231");
					user.setName("占三");
					uerDao.save(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
	}

}
