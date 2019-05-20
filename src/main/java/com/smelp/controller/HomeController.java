package com.smelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smelp.mapper.UserDao;
@RestController
@RequestMapping("/demo")
public class HomeController {
	@Autowired
	private  UserDao uerDao;
	
	 @GetMapping("/test")
	    public String home(){
	        return "Hello, Zbook!";
	    }

	 
}
