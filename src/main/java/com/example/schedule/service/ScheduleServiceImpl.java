package com.example.schedule.service;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto dto) {
        return scheduleRepository.addSchedule(dto);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulue(String author, String date) {
        return scheduleRepository.findAllSchedule(author, date);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.fineScheduleById(id);
    }

    @Override
    public int deleteSchedule(Long id, ScheduleRequestDto dto) {
        return scheduleRepository.deleteSchedule(id, dto.getPw());
    }

    @Override
    @Transactional
    public ScheduleResponseDto modifySchedule(Long id, ScheduleRequestDto dto) {
        String author = dto.getAuthor();
        String contents = dto.getContents();
        String pw = dto.getPw();
        if (pw == null || contents == null || author == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pw, contents, and author must not be empty values.");
        }
        int updateRow = scheduleRepository.modifySchedule(id, pw, author, contents);
        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id and pw are not valid values.");
        }
        return scheduleRepository.fineScheduleById(id);

    }

}
