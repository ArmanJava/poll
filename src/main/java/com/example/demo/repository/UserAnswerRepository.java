package com.example.demo.repository;

import com.example.demo.model.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {
}
