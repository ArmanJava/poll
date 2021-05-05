package com.example.demo.repository;

import com.example.demo.model.QuestionType;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsTypeRepository extends CrudRepository<QuestionType, Long> {
}
