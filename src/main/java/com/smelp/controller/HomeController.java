package com.smelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smelp.mapper.UserDao;
import com.smelp.utils.RedisUtil;
@RestController
@RequestMapping("/demo")
public class HomeController {
	@Autowired
	private  UserDao uerDao;
	@Autowired
	private RedisUtil redisUtil;
	
	 @GetMapping("/test")
	    public String home(){
	        return "Hello, Zbook!";
	    }

	 @GetMapping("/set")
	  public boolean set() {
		 boolean set = redisUtil.set("name", "12312");
		return set;
	  }
	 
	 @GetMapping("/get")
	  public String get() {
		 String object = (String) redisUtil.get("name");
		return object;
	  }
}
