package com.shafrin.taskmanagementapi.service;

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

    public String register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
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

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if(user == null) {
            return "User Not Found";
        }

        if(passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return jwtService.generateToken(user.getEmail());
        }

        return "Invalid Password";
    }
}