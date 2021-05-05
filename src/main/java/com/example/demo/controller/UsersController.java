package com.example.demo.controller;

import com.example.demo.dto.PollsResponseDto;
import com.example.demo.dto.UserAnswerDto;
import com.example.demo.model.UserAnswer;
import com.example.demo.service.PollsService;
import com.example.demo.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;
    private final PollsService pollsService;

    @Autowired
    public UsersController(UsersService usersService, PollsService pollsService) {
        this.usersService = usersService;
        this.pollsService = pollsService;
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/polls")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PollsResponseDto>> getAllActivePolls() {
        return ResponseEntity.ok(pollsService.getAllActivePolls());
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/submit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> submitPoll(@RequestBody UserAnswerDto userAnswerDto) {
        usersService.submitPoll(userAnswerDto);
        return ResponseEntity.ok(pollsService.getAllActivePolls());
    }


}
