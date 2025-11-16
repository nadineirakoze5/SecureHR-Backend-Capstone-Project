package org.example.employeemanagementapi.Service;

import lombok.Data;
import org.example.employeemanagementapi.DTOs.RegisterRequest;
import org.example.employeemanagementapi.DTOs.LoginRequest;
import org.example.employeemanagementapi.DTOs.LoginResponse;
import org.example.employeemanagementapi.Models.User;
import org.example.employeemanagementapi.Repository.UserRepository;
import org.example.employeemanagementapi.Security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        return "User registered successfully!";
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtUtil.generateToken(user);

        return new LoginResponse(token);
    }
}

