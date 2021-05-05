package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UsersService userService;

    @Autowired
    public AuthController(UsersService userService) {
        this.userService = userService;
    }

    /**
     * sign in
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody @Valid UserDto userDTO) {
        String jwt = userService.signIn(userDTO).getAccessToken();
        return ResponseEntity.ok(jwt);
    }

}
