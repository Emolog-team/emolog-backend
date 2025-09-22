package com.emolog.domain.user.service;

import com.emolog.domain.user.converter.UserConverter;
import com.emolog.domain.user.dto.request.UserLoginRequest;
import com.emolog.domain.user.dto.request.UserSignupRequest;
import com.emolog.domain.user.dto.response.UserResponse;
import com.emolog.domain.user.entity.User;
import com.emolog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse signup(UserSignupRequest request) {
        User user = UserConverter.toEntity(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return UserConverter.toResponse(user); // DTO 반환
    }

    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없음"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("유효하지 않은 비밀번호");
        }

        return UserConverter.toResponse(user); // DTO 반환
    }
}
