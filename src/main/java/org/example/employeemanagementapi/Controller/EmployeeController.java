package org.example.employeemanagementapi.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.employeemanagementapi.DTOs.EmployeeRequest;
import org.example.employeemanagementapi.DTOs.EmployeeResponse;
import org.example.employeemanagementapi.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create new employee", description = "Adds a new employee under a manager")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }


    @Operation(summary = "Get all employees", description = "Returns a list of all employee records")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }


    @Operation(summary = "Get employee by ID", description = "Fetch a single employee record using employee ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        EmployeeResponse emp = employeeService.getById(id);
        if (emp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(emp);
    }

    @Operation(summary = "Update employee", description = "Updates an existing employee by ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse updated = employeeService.update(id, request);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete employee", description = "Removes an employee by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}

