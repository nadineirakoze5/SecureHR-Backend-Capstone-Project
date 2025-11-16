package org.example.employeemanagementapi.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response returned after successful login")
public class LoginResponse {

    @Schema(description = "Generated JWT token")
    private String token;

    public LoginResponse(String token) {

        this.token = token;
    }
}
