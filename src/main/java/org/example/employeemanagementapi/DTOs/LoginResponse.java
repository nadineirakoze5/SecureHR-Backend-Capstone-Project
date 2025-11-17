package org.example.employeemanagementapi.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response returned after successful login")
public class LoginResponse {

    @Schema(description = "Generated JWT token")
    private String token;
    private Long id;

}
