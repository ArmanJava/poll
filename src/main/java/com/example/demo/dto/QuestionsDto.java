package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionsDto implements Serializable {
    private static final long serialVersionUID = 7655266012311588424L;
    private Long id;
    private String description;
    private Long questionTypeId;
    private Long pollId;
}
