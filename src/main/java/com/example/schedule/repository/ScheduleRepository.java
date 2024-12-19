package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE "
            + "(:author IS NULL OR s.author LIKE %:author%) "
            + "AND (:modifiedAt IS NULL OR DATE(s.modifiedAt) = :modifiedAt)"
            + "ORDER BY s.modifiedAt DESC")
    List<Schedule> findAllSchedule(
            @Param("author") String author,
            @Param("modifiedAt") LocalDate modifiedAt
    );

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no schedule for this id."));
    }
}