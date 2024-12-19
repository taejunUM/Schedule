package com.example.schedule.service;

import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto dto);

    List<UserResponseDto> findAllUser();

    UserResponseDto findUserById(Long id);

    UserResponseDto modifyUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);
}
