package com.shoaib.springboot.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
	// Authentication : User --> Roles
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		System.out.println("**** in security AuthenticationManagerBuilder class **** ");
		auth.inMemoryAuthentication().withUser("shoaib").password("pwd12345")
		.roles("USER").and().withUser("admin1").password("secret1")
		.roles("USER", "ADMIN");

//		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("{noop}secret1")
//				.roles("USER").and().withUser("admin1").password("secret1")
//				.roles("USER", "ADMIN");
		
//		auth.inMemoryAuthentication().withUser("in28Minutes").password("{noop}dummy")
//        .roles("USER", "ADMIN");
		
	}

	// Authorization : Role -> Access
	// survey -> USER
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("**** in security confgure class **** ");
		
		
		
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/**").hasRole("USER")
		.antMatchers("/surveys/**").hasRole("USER")
		.antMatchers("/users/**").hasRole("USER")
		.antMatchers("/welcome/**").hasRole("USER")
		.antMatchers("/http://dummy.restapiexample.com/**").hasRole("USER")
		.antMatchers("/http://dummy.restapiexample.com/**").hasRole("ADMIN")
		.and().csrf().disable()
		.headers().frameOptions().disable()
		.and().formLogin();
	}
	
	
	

}