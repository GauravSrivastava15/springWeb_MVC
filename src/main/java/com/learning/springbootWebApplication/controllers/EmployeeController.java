package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping("/employees/{employeeID}")
    public EmployeeDTO getEmployeeId(@PathVariable Long employeeId){
        return new EmployeeDTO(22, LocalDate.of(2025, 7, 27), "gaurav@123", employeeId, true,  "gaurav");
    }

}
