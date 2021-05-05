package com.example.demo.repository;

import com.example.demo.model.Questions;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepository extends CrudRepository<Questions, Long> {
}
