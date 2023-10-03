package com.example.springboot_crud.controller;

import com.example.springboot_crud.exception.ResourceNotFoundException;
import com.example.springboot_crud.model.Employee;
import com.example.springboot_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
     private EmployeeRepository employeeRepository;

    // build for getting the data of employee
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();

    }

    // building for creating the employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeRepository.save(employee);
    }

    // building for get employee by its id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exist id:" +id)
        );
        return ResponseEntity.ok(employee);
    }

    // for updating the employee
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" + id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }
    // for deleting employee by id
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id:"+id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
