package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String userName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

}
