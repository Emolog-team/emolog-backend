package com.emolog.domain.user.controller;

import com.emolog.domain.user.dto.request.UserSignupRequest;
import com.emolog.domain.user.dto.request.UserLoginRequest;
import com.emolog.domain.user.dto.response.UserResponse;
import com.emolog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request); // DTO만 받음
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request); // DTO만 받음
    }
}
