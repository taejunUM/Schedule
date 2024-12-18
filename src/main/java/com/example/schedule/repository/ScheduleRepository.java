package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;


public interface ScheduleRepository {
    ScheduleResponseDto addSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedule(String author, String date);

    ScheduleResponseDto fineScheduleById(Long id);

    int modifySchedule(Long id, String pw, String author, String contents);

    int deleteSchedule(Long id, String pw);
}