package com.shoaib.springboot.controller;


import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import com.shoaib.springboot.mySpringBootJPA;
import com.shoaib.springboot.model.Question;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= mySpringBootJPA.class,webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT2 {

	// setting it to local git from local dev 
	@LocalServerPort
	private int port;
	
	public String BaseUrl = "http://localhost:";
	
	TestRestTemplate testRestTemp = new TestRestTemplate();
	HttpHeaders httpHeader = new HttpHeaders();
	
	@Before
	public void before() {
		httpHeader.add("Authorization", createHttpAuthenticationHeaderValue(
				"user1", "secret1"));
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

		private String createHttpAuthenticationHeaderValue(String userId,
				String password) {

			String auth = userId + ":" + password;

			byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));

			String headerValue = "Basic " + new String(encodedAuth);

			return headerValue;
		}


	// 
	@Test
	public void testAllQuestionFromSurvey() {
		
		System.out.println("Base url  is " + BaseUrl);
		String url = BaseUrl + port+ "/surveys/Survey1/questions";
		System.out.println("port is " + port);
		System.out.println("url  is " + url);
		
		//String output = testRestTemp.getForObject(url, String.class);
		
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		System.out.println(" invoked http headers ");
		HttpEntity entity = null;
		entity = new HttpEntity<String>(null,httpHeader);
		System.out.println("invoked entity" );
		ResponseEntity<List<Question>> response = testRestTemp.exchange(url,
				HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
						httpHeader),
				new ParameterizedTypeReference<List<Question>>() {
				});

		System.out.println("response "+response.getBody() );
		
		Question sampleQuestion = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));

		assertTrue(response.getBody().contains(sampleQuestion));	}

	@Test
	public void testOneQuestionFromSurvey() {
		
		String url = BaseUrl + "/surveys/Survey1/questions/Question2";
		System.out.println("Question 2 port is " + port);
		System.out.println("url  is " + url);
		//String output = testRestTemp.getForObject(url, String.class);
		
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = null;
		entity = new HttpEntity<String>(null,httpHeader);
		ResponseEntity<String> response = null;
		response = testRestTemp.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println("response  is " + response);
		System.out.println("response  is " + response.getBody());
		String expected = "{id:Question2,description:Most Populus Country in the World,correctAnswer:China}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		//assertTrue(response.getBody().contains("id\":\"Question2"));
	}

	@Test
	public void testAddQuestionForSurvey() {
		
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/";

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Question question = new Question("DOESNTMATTER", "Question1", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);

		System.out.println("actual calling" );
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println("actual " + actual);
		
		assertTrue(actual.contains("/surveys/Survey1/questions/"));	
}

}