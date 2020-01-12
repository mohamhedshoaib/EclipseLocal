package com.shoaib.springboot.controller;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.xml.ws.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.shoaib.springboot.model.Question;
import com.shoaib.springboot.service.SurveyService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class , secure = false)

public class SurveyControllerMockitoTesting {

	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private SurveyService surveyServicess;
	
	
	@Test
	public void retrieveDataForQuestions() {
		
		Question question1 = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));

		
		Mockito.when(surveyServicess.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1").accept(MediaType.APPLICATION_JSON);
		
		try {
			MvcResult result =  mockMVC.perform(requestBuilder).andReturn();
			
			String expected = "{id:Question1,description:Most Populus Country in the World,correctAnswer:China}";
			
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
			
			System.out.println("resulttt " + result.getResponse());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Not yet implemented");
	}

	
	@Test
	public void createSurveyQuestion() throws Exception {
		Question mockQuestion = new Question("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		//surveyService.addQuestion to respond back with mockQuestion
		Mockito.when(
				surveyServicess.addQuestion(Mockito.anyString(), Mockito
						.any(Question.class))).thenReturn(mockQuestion);

		//Send question as body to /surveys/Survey1/questions
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(questionJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("HttpStatus.CREATED " +HttpStatus.CREATED);
		
		System.out.println("response. " +response.getStatus());

		System.out.println("response. " +response.getHeader(HttpHeaders.LOCATION));
		
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/surveys/Survey1/questions/1", response
				.getHeader(HttpHeaders.LOCATION));
	}
	

	@Test
	public void retrieveEmployeeForQuestions() {
		
//		Question question1 = new Question("Question1",
//				"Largest Country in the World", "Russia", Arrays.asList(
//						"India", "Russia", "United States", "China"));
	
//		Mockito.when(surveyServicess.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://dummy.restapiexample.com/api/v1/employee/83245").accept(MediaType.APPLICATION_JSON);
		
		try {
			MvcResult result =  mockMVC.perform(requestBuilder).andReturn();
			
			String expected = "{id:83245,employee_name:testtop}";
			
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
			
			System.out.println("resulttt " + result.getResponse());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Not yet implemented");
	}


	
	
}
