package com.example.schedule.service;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EntityManager em;


    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto dto) {
        // 유저생성X 세션에 있는 이메일 또는 id로 유저객체를 불러옴
        // 그 후 일정생성 할때 유저객체를 같이 넘겨줌.
        User user = new User("name", "abc@aaa.com", "password");
        user.setId(1L);

        Schedule schedule = new Schedule(dto.getAuthor(), dto.getTitle(), dto.getContents(), user);
        Schedule save = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toDto(save);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulue(String author, String date) {
        LocalDate localDate = null;

        if (date != null && !date.isBlank()) {
            localDate = LocalDate.parse(date);
        }
        List<Schedule> schedules =  scheduleRepository.findAllSchedule(author,localDate);
        return  schedules.stream()
                .map(ScheduleResponseDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponseDto.toDto(schedule);
    }

    @Override
    @Transactional
    public ScheduleResponseDto modifySchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        String author = dto.getAuthor();
        String title = dto.getTitle();
        String contents = dto.getContents();

        schedule.updateSchedule(author, title, contents);
        em.flush();//메소드가 끝나기전에 커밋함.(변경사항 즉시 반영)
        return ScheduleResponseDto.toDto(schedule);
    }
    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);

    }
}
