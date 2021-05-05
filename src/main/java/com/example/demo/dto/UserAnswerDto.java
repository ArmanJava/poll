package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserAnswerDto implements Serializable {
    private static final long serialVersionUID = -3901233034685246792L;
    private String userId;
    private Long questionId;
    private List<AnswerDto> answerDtos = new ArrayList<>();

    @Data
    public static class AnswerDto implements  Serializable{

        private static final long serialVersionUID = -760215785134113739L;
        private Long questionId;
        private String textAnswer;
        private List<Long> answerOptionId;

    }
}
