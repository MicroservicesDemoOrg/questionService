package com.kanchankk.questionService.service;

import java.util.List;

import com.kanchankk.questionService.models.Question;
import com.kanchankk.questionService.to.QuestionTO;
import com.kanchankk.questionService.to.SubmitResponseTO;

public interface IQuestionService {
	public List<Question> fetchAllQuestions();
	
	public List<Question> fetchQuestionsByCategory(String category);

	public String addQuestion(Question question);

	public List<Integer> fetchQuestionForQuiz(String category, Integer numOfQuestion);

	public List<QuestionTO> fetchQuestionsByIds(List<Integer> idList);

	public Integer processResult(List<SubmitResponseTO> quizSubmission);
}
