package com.example.chat_app_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
         .csrf(csrf -> csrf.disable()) // Disables CSRF for simplicity (enable with tokens in production)
         .authorizeHttpRequests(auth -> auth
          .requestMatchers("/api/auth/login","/api/auth/register").permitAll()
          .requestMatchers("/ws/**").permitAll()
         .anyRequest().authenticated() // Secure all other endpoints
         )
         .httpBasic(withDefaults()); // Enable HTTP Basic authentication
         return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}