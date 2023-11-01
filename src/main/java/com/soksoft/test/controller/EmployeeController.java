package com.soksoft.test.controller;

import com.soksoft.test.domain.Employee;
import com.soksoft.test.payload.ApiResposnse;
import com.soksoft.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), CREATED);
    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        return new ResponseEntity<>(employeeService.getEmployeeById(id), FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){

        Optional<Employee> employee1 = Optional.ofNullable(employeeService.updateEmployee(id, employee));
        return employee1.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResposnse> deleteEmployee(@PathVariable Long id){
        ApiResposnse apiResposnse = employeeService.deleteEmployee(id);
        if (apiResposnse != null && apiResposnse.getMessage().equals("Employee deleted successfully")) {
            return new ResponseEntity<>(apiResposnse, NO_CONTENT);
        } else {
            return new ResponseEntity<>(apiResposnse, NOT_FOUND);
        }
    }


}
