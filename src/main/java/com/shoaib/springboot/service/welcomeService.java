package com.shoaib.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class welcomeService{
	
	
	@Value("${welcome.message}")
	private String welcomeMessageVar;
	
	public String retrieveWelcomeMessage(){
		
		return welcomeMessageVar;
	}
}
