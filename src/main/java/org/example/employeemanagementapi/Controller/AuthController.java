package org.example.employeemanagementapi.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.employeemanagementapi.DTOs.RegisterRequest;
import org.example.employeemanagementapi.DTOs.LoginRequest;
import org.example.employeemanagementapi.DTOs.LoginResponse;
import org.example.employeemanagementapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Data
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Register a new user", description = "Creates a new user and stores their details in the database")
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @Operation(summary = "Login user", description = "Authenticates user and returns a JWT token")
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return userService.login(request);
    }
}
