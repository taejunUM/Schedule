package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleRequestDto {

    private String author;
    private String title;
    private String contents;


    }
