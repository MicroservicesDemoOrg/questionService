package com.kanchankk.questionService.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.kanchankk.questionService.models.Question;
import com.kanchankk.questionService.repository.QuestionRepository;
import com.kanchankk.questionService.service.IQuestionService;
import com.kanchankk.questionService.to.QuestionTO;
import com.kanchankk.questionService.to.SubmitResponseTO;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	Environment environment;

	@Override
	public List<Question> fetchAllQuestions() {
		List<Question> questionList = null;

		questionList = questionRepository.findAll();

		if (questionList == null || questionList.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No data found");
		}
		return questionList;
	}

	@Override
	public List<Question> fetchQuestionsByCategory(String category) {

		List<Question> questionList = null;

		questionList = questionRepository.findByCategory(category);

		if (questionList == null || questionList.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No data found");
		}

		return questionList;
	}

	@Override
	public String addQuestion(Question question) {
		questionRepository.save(question);

		return "success";

	}

	@Override
	public List<Integer> fetchQuestionForQuiz(String category, Integer numOfQuestion) {
		List<Integer> questionList = questionRepository.findRandomQuestionsByCategory(category, numOfQuestion);
		
		if (questionList == null || questionList.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No data found");
		}

		return questionList;
	}

	@Override
	public List<QuestionTO> fetchQuestionsByIds(List<Integer> idList) {
		List<Question> questionList = questionRepository.findAllById(idList);

		System.out.println(environment.getProperty("local.server.port"));
		if (questionList == null || questionList.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No data found");
		}
		
		return questionList.stream()
				.map(question -> new QuestionTO(
						question.getId(),
						question.getQuestionTitle(),
						question.getOption1(),
						question.getOption2(),
						question.getOption3(),
						question.getOption4()
						)
					)
				.collect(Collectors.toList());

	}

	@Override
	public Integer processResult(List<SubmitResponseTO> quizSubmission) {

		List<Integer> questionIdList = quizSubmission.stream()
										.map(SubmitResponseTO::getId)
										.collect(Collectors.toList());
		
		List<Question> questionList = questionRepository.findAllById(questionIdList);
		
		Map<Integer, String> ansSheet = questionList.stream()
										.collect(Collectors.toMap(Question::getId, Question::getRightAnswer));
		
		return (int) quizSubmission.stream()
				.filter(response -> response.getResponse().equalsIgnoreCase(ansSheet.get(response.getId())))
				.count();
	}

}
