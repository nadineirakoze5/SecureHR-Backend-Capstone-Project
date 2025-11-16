package org.example.employeemanagementapi.Service;

import lombok.RequiredArgsConstructor;
import org.example.employeemanagementapi.DTOs.EmployeeRequest;
import org.example.employeemanagementapi.DTOs.EmployeeResponse;
import org.example.employeemanagementapi.Models.Employee;
import org.example.employeemanagementapi.Models.User;
import org.example.employeemanagementapi.Repository.EmployeeRepository;
import org.example.employeemanagementapi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager with ID " + request.getManagerId() + " not found"));

        if (!manager.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only ADMIN users can be managers");
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setDepartment(request.getDepartment());
        employee.setHireDate(request.getHireDate());
        employee.setManager(manager);

        Employee saved = employeeRepository.save(employee);

        return toResponse(saved);
    }

    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
        return toResponse(employee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));

        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager with ID " + request.getManagerId() + " not found"));

        if (!manager.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only ADMIN users can be managers");
        }

        existing.setName(request.getName());
        existing.setPosition(request.getPosition());
        existing.setDepartment(request.getDepartment());
        existing.setHireDate(request.getHireDate());
        existing.setManager(manager);

        Employee updated = employeeRepository.save(existing);
        return toResponse(updated);
    }

    public String delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            return "Employee not found";
        }
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    private EmployeeResponse toResponse(Employee employee) {
        Long managerId = employee.getManager() != null ? employee.getManager().getId() : null;
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setPosition(employee.getPosition());
        response.setDepartment(employee.getDepartment());
        response.setHireDate(employee.getHireDate());
        response.setManagerId(managerId);
        return response;
    }
}
