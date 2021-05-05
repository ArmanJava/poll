package com.example.demo.service;

import com.example.demo.dto.RegistrationDto;
import com.example.demo.dto.UserAnswerDto;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.Roles;
import com.example.demo.model.*;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.security.JwtAuthenticationResponseDto;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final QuestionsRepository questionsRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, RoleRepository roleRepository, PasswordEncoder passwordEncoder, QuestionsRepository questionsRepository) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        this.questionsRepository = questionsRepository;
    }


    public void save(Users newUsers) {
        usersRepository.save(newUsers);
    }

    public JwtAuthenticationResponseDto signIn(UserDto userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        log.info("User with [email: {}] has logged in", userPrincipal.getEmail());
        return new JwtAuthenticationResponseDto(jwt);
    }

    public void createUsers(RegistrationDto registrationDTO) {
        Users user = new Users();
        user.setEmail(registrationDTO.getEmail());
        user.setLastname(registrationDTO.getLastname());
        user.setName(registrationDTO.getName());
        Role role = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new ObjectNotFoundException(Role.class, "Role not found"));
        user.setRoles(Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        usersRepository.save(user);

    }

    public boolean submitPoll(UserAnswerDto userAnswerDto) {
        userAnswerDto.getAnswerDtos().forEach(a->{
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setAnonUserId(userAnswerDto.getUserId());
            userAnswer.setTextAnswer(a.getTextAnswer());
            Questions question = questionsRepository.findById(userAnswerDto.getQuestionId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));

            userAnswer.setQuestions(question);
        });
        return  true;

    }
}
