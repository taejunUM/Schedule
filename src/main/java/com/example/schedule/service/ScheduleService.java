package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService{

    ScheduleResponseDto addSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedulue(String author, String date);

    ScheduleResponseDto modifySchedule(Long id, ScheduleRequestDto dto);

    ScheduleResponseDto findScheduleById(Long id);

    void deleteSchedule(Long id);
}