package org.example.employeemanagementapi.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request body for creating or updating an employee")
public class EmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @NotNull(message = "Manager ID is required")
    private Long managerId;

}

