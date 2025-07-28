package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import com.learning.springbootWebApplication.entities.EmployeeEntity;
import com.learning.springbootWebApplication.repositories.EmployeeRepository;
import com.learning.springbootWebApplication.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeID){
        return employeeService.getEmployeeById(employeeID);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "newAge") Integer age, @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployees){
        return employeeService.createNewEmployee(inputEmployees);
    }

}
