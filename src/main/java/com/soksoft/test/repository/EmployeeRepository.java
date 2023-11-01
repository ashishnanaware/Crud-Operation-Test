package com.soksoft.test.repository;

import com.soksoft.test.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void delete(Employee employee);
    Optional<Employee> findByEmail(String email);
}
