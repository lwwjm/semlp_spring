package com.smelp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smelp.core.UserDao;
import com.smelp.mapper.UserMapper;
import com.smelp.pojo.DUser;
import com.smelp.service.EventLoopCallback;
import com.smelp.service.RunPoolThread;
import com.smelp.utils.RedisTool;
import com.smelp.utils.RedisUtil;
import com.smelp.utils.ThreadRunPool;
@RestController
@RequestMapping("/demo")
public class HomeController {
	@Autowired
	private  UserDao uerDao;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RedisTool redisTool;
	@Autowired
	private ThreadRunPool  threadRunPool;
	@Autowired
	private EventLoopCallback eventLoopCallback;
	@Autowired
	private  UserMapper  userMapper;
	
	 @GetMapping("/test")
	    public String home(){
	        return "Hello, Zbook!";
	    }

	@GetMapping("/set")
	  public String set() {
	    String lock=null;
		try {
			redisUtil.set("name", "5679");
			lock = redisTool.lock("id", 345L, 23L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lock;
	  }
	 
	 @GetMapping("/get")
	  public String get() {
		 String object = (String) redisUtil.get("id");
		return object;
	  }
	 
	 @GetMapping("/getUser")
	  public List<DUser> getUser(String name) {
		 DUser  user=new DUser();
		 user.setName(name);
		 @SuppressWarnings("rawtypes")
		Specification querySpeci = new Specification() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
				 List<Predicate> predicates = new ArrayList();
				 if(!StringUtils.isEmpty(user.getName())) {
	                    predicates.add(criteriaBuilder
	                            .equal(root.get("name"), user.getName()));
	                }
				 return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			 
		 };
		 
	List findAll2 = uerDao.findAll(querySpeci);
	return findAll2;
	  }
	 
	 
	 
	 @PostMapping("/run")
	 public String threadRun(){
//		 ExecutorService e = threadRunPool.threadRun();
//		 e.execute(new RunPoolThread());
		 Callable<String> callback = eventLoopCallback.callback("12313");
		 System.out.println(callback);
		return null;
	 }
	 
	 
	 
	 @PostMapping("/findUser")
	 @ResponseBody
	 public Map<String,Object> findUser(){
		 
		
		return  userMapper.findUser();
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
