package com.rawen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawen.models.Question;

public interface QuestionRepsitory extends JpaRepository<Question, Long> {

	  List<Question> findByCategoryId(Long categoryId);


}
