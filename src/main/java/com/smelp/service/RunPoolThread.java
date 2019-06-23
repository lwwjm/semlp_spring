package com.smelp.service;

import org.springframework.stereotype.Service;

@Service
public class RunPoolThread implements RunPool{

	
	
	@Override
	public void run() {
	
      System.out.println("-==线程执行ok==");
		
	}
	
	
	
	

}
