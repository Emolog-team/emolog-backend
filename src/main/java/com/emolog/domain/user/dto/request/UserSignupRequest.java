package com.emolog.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupRequest {
    @NotBlank
    @Size(min=2, max=10)
    private String username;

    @NotBlank
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$",
            message = "이메일 형식에 맞지 않음."
    )
    private String email;

    @NotBlank
    @Size(min=8, max=64, message= "비밀번호는 8자리에서 64자리여야 함")
    @Pattern(regexp="^(?=.*\\d)(?=.*[A-Za-z]).+$",
            message="영문자와 숫자를 최소 1개씩 포함해야 함")
    private String password;
}
