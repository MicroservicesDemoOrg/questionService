package com.kanchankk.questionService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kanchankk.questionService.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);
	
	@Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :noOfQuestions", nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(String category, Integer noOfQuestions);
}
