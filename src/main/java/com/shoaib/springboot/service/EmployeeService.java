package com.shoaib.springboot.service;




import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.shoaib.springboot.model.Employee;


@Component
public class EmployeeService {

	//private static List<Survey> surveys = new ArrayList<>();
	
	private static List<Employee> employee = new ArrayList<>();
	
//	static {
//		Question question1 = new Question("Question1",
//				"Largest Country in the World", "Russia", Arrays.asList(
//						"India", "Russia", "United States", "China"));
//		Question question2 = new Question("Question2",
//				"Most Populus Country in the World", "China", Arrays.asList(
//						"India", "Russia", "United States", "China"));
//		Question question3 = new Question("Question3",
//				"Highest GDP in the World", "United States", Arrays.asList(
//						"India", "Russia", "United States", "China"));
//		Question question4 = new Question("Question4",
//				"Second largest english speaking country", "India", Arrays
//						.asList("India", "Russia", "United States", "China"));
//
//		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
//				question2, question3, question4));
//
//		Survey survey = new Survey("Survey1", "My Favorite Survey",
//				"Description of the Survey", questions);
//
//		surveys.add(survey);
//	}

	public List<Employee> retrieveAllSurveys() {
		return employee;
	}
//	public Employee retrieveQuestion(String employeeId) {
//		System.out.println("here in service ");
//		for (Employee emps : employee) {
//			if (emps.getId().equals(employeeId)) {
//				return emps;
//			}
//		}
//
//		return null;
//	}


	public Employee retrieveQuestion(String employeeId) {
		System.out.println("here in service ");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		RestTemplate restService = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		//Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);  
		restService.setMessageConverters(messageConverters); 

		 
		System.out.println("here in service 2");
		String url = "http://dummy.restapiexample.com/api/v1/employee/"+employeeId;
		System.out.println("url " + url);
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		System.out.println("here in service 3 " + employeeId);
		
		ResponseEntity<Employee> respEntity = null;
		Employee employeeObj = new Employee();
		try {
			respEntity = restService.exchange(url, HttpMethod.GET,httpEntity,Employee.class);
			System.out.println("here in service 4");
			System.out.println("resp " + respEntity.getBody());
			
			employeeObj.setEmployee_age(respEntity.getBody().getEmployee_age());
			employeeObj.setEmployee_name(respEntity.getBody().getEmployee_name());
			employeeObj.setEmployee_salary(respEntity.getBody().getEmployee_salary());
			employeeObj.setId(respEntity.getBody().getId());
			employeeObj.setProfile_image(respEntity.getBody().getProfile_image());
			
		} catch(Exception e){
			
			System.out.println("here in EXCEPTION ");
		}
		
//		return respEntity;
		return employeeObj;

	}

}