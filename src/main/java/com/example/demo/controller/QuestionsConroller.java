package com.example.demo.controller;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionsConroller {
    private final QuestionService questionService;

    public QuestionsConroller( QuestionService questionService) {
        this.questionService = questionService;
       }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody QuestionsDto questionsDto) {
        if (questionService.addQuestion(questionsDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody QuestionsDto questionsDto) {
        if (questionService.editQuestion(questionsDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (questionService.deleteQuestion(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
}
