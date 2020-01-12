package com.shoaib.springboot.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName;
	private String role;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private TestBean() {}
	public TestBean(String userName, String role) {
		super();
		this.userName = userName;
		this.role = role;
	}
	@Override
	public String toString() {
		return "TestBean [userId=" + userId + ", userName=" + userName + ", role=" + role + "]";
	}
	
	
	
}
