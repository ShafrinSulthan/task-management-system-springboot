package com.shafrin.taskmanagementapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shafrin.taskmanagementapi.dto.LoginRequest;
import com.shafrin.taskmanagementapi.dto.RegisterRequest;
import com.shafrin.taskmanagementapi.entity.User;
import com.shafrin.taskmanagementapi.repository.UserRepository;
import com.shafrin.taskmanagementapi.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String register(RegisterRequest request) {

        logger.info("User registration started for {}", request.getEmail());

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {

            logger.warn("Registration failed. Email already exists: {}", request.getEmail());

            return "Email already exists";
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );
        user.setRole("USER");
        userRepository.save(user);

        logger.info("User registered successfully: {}", request.getEmail());

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        logger.info("Login attempt for {}", request.getEmail());

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if(user == null) {

            logger.warn("Login failed. User not found: {}", request.getEmail());

            return "User Not Found";
        }

        if(passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            logger.info("Login successful for {}", request.getEmail());

            return jwtService.generateToken(user.getEmail());
        }

        logger.warn("Invalid password for {}", request.getEmail());
        return "Invalid Password";
    }
}