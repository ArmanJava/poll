package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class PollsDto implements Serializable {
    private static final long serialVersionUID = -6523210852997606059L;
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Boolean active;
}
