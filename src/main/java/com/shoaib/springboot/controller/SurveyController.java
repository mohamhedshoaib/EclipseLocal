package com.shoaib.springboot.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shoaib.springboot.model.Question;
import com.shoaib.springboot.service.SurveyService;

@RestController
class SurveyController {
	@Autowired
	private SurveyService surveyService;

	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestions(@PathVariable String surveyId) {
		System.out.println("**** in /surveys/{surveyId}/questions **** ");
		return surveyService.retrieveQuestions(surveyId);
	}

	// GET "/surveys/{surveyId}/questions/{questionId}"
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId,
			@PathVariable String questionId) {
		System.out.println("**** in /surveys/{surveyId}/questions/{questionId} **** ");
		return surveyService.retrieveQuestion(surveyId, questionId);
	}

	// /surveys/{surveyId}/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(
			@PathVariable String surveyId, @RequestBody Question newQuestion) {
		System.out.println("**** Posttttttttttttttt in /surveys/{surveyId}/questions **** ");
		Question question = surveyService.addQuestion(surveyId, newQuestion);

		if (question == null)
			return ResponseEntity.noContent().build();

		// Success - URI of the new resource in Response Header
		// Status - created
		// URI -> /surveys/{surveyId}/questions/{questionId}
		// question.getQuestionId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(question.getId()).toUri();

		// Status
		return ResponseEntity.created(location).build();
	}
 
}