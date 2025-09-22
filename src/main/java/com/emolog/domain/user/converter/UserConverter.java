package com.emolog.domain.user.converter;

import com.emolog.domain.user.dto.response.UserResponse;
import com.emolog.domain.user.dto.request.UserSignupRequest;
import com.emolog.domain.user.entity.User;

public class UserConverter {

    //회원가입 요청 DTO를 받아서 DB에 저장할 User 엔티티로 변환
    public static User toEntity(UserSignupRequest request, String encodedPassword) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
    }

    //DB에서 꺼낸 User 엔티티를 응답 DTO(UserResponse)로 변환
    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
