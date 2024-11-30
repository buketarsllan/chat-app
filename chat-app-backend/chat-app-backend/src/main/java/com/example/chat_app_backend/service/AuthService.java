package com.example.chat_app_backend.service;

import com.example.chat_app_backend.entity.User;
import com.example.chat_app_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {

        Optional<User> existingUser = userRepository.findByUsername(username); // Update based on return type
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Hash password
        userRepository.save(user);
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User user = optionalUser.get();
        boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());

        if (!passwordMatches) {
            return "Invalid password";
        }

        return "Login successful!";
    }



}
