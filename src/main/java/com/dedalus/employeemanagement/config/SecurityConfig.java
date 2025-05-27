package com.dedalus.employeemanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String VERSION_ONE_BASE_PREFIX = "/api/v1";

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // TODO Enable csrf
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize  -> {
            authorize.requestMatchers(
                    VERSION_ONE_BASE_PREFIX + "/department/**",
                    VERSION_ONE_BASE_PREFIX + "/employee/**"
            ).permitAll();
            authorize.anyRequest().denyAll();
        }).build();
    }
}
