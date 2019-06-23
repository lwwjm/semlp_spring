package com.smelp.service;

import java.util.concurrent.Callable;

public interface EventLoopCallback {
	
	
	
	Callable<String> callback(String str);

}
