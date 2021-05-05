package com.example.demo.controller;

import com.example.demo.dto.PollsDto;
import com.example.demo.service.PollsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polls")
public class PollsController {
    private final PollsService pollsService;

    public PollsController(PollsService pollsService) {
        this.pollsService = pollsService;
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PollsDto pollsDto) {
        if (pollsService.addPoll(pollsDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody PollsDto pollsDto) {
        if (pollsService.editPoll(pollsDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (pollsService.deletePoll(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
}
