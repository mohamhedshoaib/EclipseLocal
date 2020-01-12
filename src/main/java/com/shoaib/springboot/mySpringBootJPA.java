package com.shoaib.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class mySpringBootJPA {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(mySpringBootJPA.class, args);

	}

	@Profile("dev")
	@Bean
	public String dummyBean(){
		return "dummy";
	}
	
}