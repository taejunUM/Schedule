package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {

    private Long id;
    private String author;
    private String title;
    private String contents;
    private LocalDateTime date;

}
