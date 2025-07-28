package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import com.learning.springbootWebApplication.entities.EmployeeEntity;
import com.learning.springbootWebApplication.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeId(@PathVariable Long employeeID){
        return employeeRepository.findById(employeeID).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "newAge") Integer age, @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployees){
        return employeeRepository.save(inputEmployees);
    }

}
