package com.example.schedule.controller;

import com.example.schedule.dto.LoginRequestDto;
import com.example.schedule.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto dto) {
        loginService.login(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
