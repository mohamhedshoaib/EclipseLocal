package com.shoaib.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("basic")
public class BasicConfigurations {
	
	private boolean value;
	private String message;
	private int numbers;

	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numbers;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicConfigurations other = (BasicConfigurations) obj;
		if (numbers != other.numbers)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BasicConfigurations [value=" + value + ", message=" + message + ", numbers=" + numbers + "]";
	}
	

}
