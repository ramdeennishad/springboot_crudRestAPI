package com.example.springboot_crud.repository;

import com.example.springboot_crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    // all crud databases
}
