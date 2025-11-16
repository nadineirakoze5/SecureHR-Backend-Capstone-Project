package org.example.employeemanagementapi.Repository;

import org.example.employeemanagementapi.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

