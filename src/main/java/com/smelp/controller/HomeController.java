package com.smelp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smelp.mapper.UserDao;
import com.smelp.pojo.DUser;
import com.smelp.utils.RedisUtil;
@RestController
@RequestMapping("/demo")
public class HomeController {
	@Autowired
	private  UserDao uerDao;
	@Autowired
	private RedisUtil redisUtil;
	private List findAll;
	
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
}
