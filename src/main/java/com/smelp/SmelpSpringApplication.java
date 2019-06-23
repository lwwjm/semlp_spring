package com.smelp;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.smelp.mapper")
@EnableTransactionManagement
public class SmelpSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmelpSpringApplication.class, args);
	}

}
