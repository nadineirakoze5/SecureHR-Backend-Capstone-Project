package org.example.employeemanagementapi.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response object representing employee details")
public class EmployeeResponse {

    private Long id;
    private String name;
    private String position;
    private String department;
    private LocalDate hireDate;
    private Long managerId;

}
