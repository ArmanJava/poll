package com.example.demo.model;

import com.example.demo.enums.QuestionsEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer code;
    @Enumerated(EnumType.STRING)
    @NaturalId
    private QuestionsEnum name;
}
