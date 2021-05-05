package com.example.demo.dto;

import com.example.demo.model.Polls;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PollsResponseDto implements Serializable {
    private static final long serialVersionUID = 1937160166676602957L;
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Boolean active;
    private List<QuestionsDto> questionsDtos = new ArrayList<>();
    public static PollsResponseDto create(Polls polls){
        PollsResponseDto pollsResponseDto = new PollsResponseDto();
        pollsResponseDto.setName(polls.getName());
        pollsResponseDto.setDescription(polls.getDescription());
        pollsResponseDto.setStartDate(polls.getStartDate());
        pollsResponseDto.setEndDate(polls.getEndDate());
        pollsResponseDto.setActive(polls.isActive());
        pollsResponseDto.setId(polls.getId());
        polls.getQuestions().forEach(q->{
            QuestionsDto questionsDto = new QuestionsDto();
            questionsDto.setId(q.getId());
            questionsDto.setDescription(q.getDescription());
            questionsDto.setQuestionTypeId(q.getQuestionType().getId());
            pollsResponseDto.getQuestionsDtos().add(questionsDto);

        });
        return pollsResponseDto;
    }
}
