package com.kanchankk.questionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kanchankk.questionService.models.Question;
import com.kanchankk.questionService.service.IQuestionService;
import com.kanchankk.questionService.to.QuestionTO;
import com.kanchankk.questionService.to.SubmitResponseTO;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	IQuestionService questionService;
	
	@GetMapping("/allQuestion")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return ResponseEntity.ok(questionService.fetchAllQuestions());
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
		return ResponseEntity.ok(questionService.fetchQuestionsByCategory(category));
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestion){
		return ResponseEntity.ok(questionService.fetchQuestionForQuiz(category, numOfQuestion));
	}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionTO>> getQuestionsByIds(@RequestBody List<Integer> idList){
		return ResponseEntity.ok(questionService.fetchQuestionsByIds(idList));
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<SubmitResponseTO> quizSubmission){
		return ResponseEntity.ok(questionService.processResult(quizSubmission));
	}
}
