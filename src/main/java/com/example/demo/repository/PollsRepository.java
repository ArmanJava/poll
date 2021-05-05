package com.example.demo.repository;

import com.example.demo.model.Polls;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PollsRepository extends CrudRepository<Polls, Long> {
    List<Polls> findByActiveTrue();
}
