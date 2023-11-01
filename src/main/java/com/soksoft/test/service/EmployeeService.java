package com.soksoft.test.service;
import com.soksoft.test.domain.Employee;
import com.soksoft.test.payload.ApiResposnse;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(long id, Employee employee);
    ApiResposnse deleteEmployee(Long id);

}
