package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       private String anonUserId;
    @ManyToOne
    @JoinColumn(name = "questions_id")
    private Questions questions;

    @ManyToMany
    @JoinTable(
            name = "link_user_answer_answer_options",
            joinColumns = @JoinColumn(name = "user_answer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_options_id"))
    private Set<AnswerOptions> answerOptions = new HashSet<>();
    private String textAnswer;
    private LocalDateTime time;

}
