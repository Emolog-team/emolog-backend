package com.emolog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/api-docs/**",
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // cors 비활성화
        http
                .cors(cors -> cors
                        .configurationSource(CorsConfig.apiConfigurationSource()));

        // csrf disable
        http
                .csrf(AbstractHttpConfigurer::disable);

        // form 로그인 방식 disable
        http
                .formLogin(withDefaults());

        // http basic 인증 방식 disable
        http
                .httpBasic(withDefaults());

        // Session을 사용하지 않고, Stateless 서버를 만듬.
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 경로별 인가
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(allowUrl).permitAll()
                        .requestMatchers("/**").permitAll()
//                        .anyRequest().authenticated()
                );


        return http.build();
    }
}
