package com.shoaib.springboot.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoaib.springboot.configuration.BasicConfigurations;
import com.shoaib.springboot.service.welcomeService;

@RestController
public class WelcomeController {
	
	//@ComponentScan , @Controller, @Service , @Repository , @Autowired , @RestController
	
	@Autowired
	welcomeService wlcomeService;
	
	@Autowired
	BasicConfigurations basicConfig;
	
	@RequestMapping("/welcome")
	public String welcome(){
		
		//return userRepo.findByRole("Sales");
		
		System.out.println("*************** in welcome controller");
		
		//return wlcomeService.retrieveWelcomeMessage();
		return "welcome2";
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map getConfigs(){
		Map map = new HashMap();
		map.put("message", basicConfig.getMessage());
		map.put("number", basicConfig.getNumbers());
		map.put("value", basicConfig.isValue());
		return map ;
	}
	
	
	
	
}

