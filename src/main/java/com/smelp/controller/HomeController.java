package com.smelp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/demo")
public class HomeController {
	
	 @GetMapping("/test")
	    public String home(){
	        return "Hello, Zbook!";
	    }

}
