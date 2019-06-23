package com.smelp.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;
@Component
public class ThreadRunPool{
	
	
	
	public ExecutorService  threadRun(){
	    ExecutorService e = Executors.newSingleThreadExecutor();
		return e;	
	}

	

}
