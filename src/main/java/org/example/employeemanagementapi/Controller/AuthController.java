package org.example.employeemanagementapi.Controller;

import org.example.employeemanagementapi.DTOs.RegisterRequest;
import org.example.employeemanagementapi.DTOs.LoginRequest;
import org.example.employeemanagementapi.DTOs.LoginResponse;
import org.example.employeemanagementapi.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }
}
