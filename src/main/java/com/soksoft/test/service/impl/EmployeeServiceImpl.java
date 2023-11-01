package com.soksoft.test.service.impl;

import com.soksoft.test.domain.Employee;
import com.soksoft.test.exception.ResourceFoundException;
import com.soksoft.test.exception.ResourceNotFoundException;
import com.soksoft.test.payload.ApiResposnse;
import com.soksoft.test.repository.EmployeeRepository;
import com.soksoft.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {

        Optional<Employee> empByEmail = employeeRepository.findByEmail(employee.getEmail());
        if (empByEmail.isPresent()) {
            throw new ResourceFoundException("Email already registered");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " is not found in server"));
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {

        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            Optional<Employee> empByEmail = employeeRepository.findByEmail(employee.getEmail());
            if (empByEmail.isPresent()) {
                throw new ResourceFoundException("Email already registered");
            }

            return employeeRepository.save(employee) ;
        }
        return null;

    }

    @Override
    public ApiResposnse deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return new ApiResposnse("Employee deleted successfully", NO_CONTENT);
        }else return new ApiResposnse("Resource not found", NOT_FOUND);
    }
}
