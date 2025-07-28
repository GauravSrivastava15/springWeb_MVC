package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeId(@PathVariable Long employeeID){
        return new EmployeeDTO(22, LocalDate.of(2025, 7, 27), "gaurav@123", employeeID, true,  "gaurav");
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "newAge") Integer age, @RequestParam(required = false) String sortBy){
        return "Hi age is " + age + " " + sortBy;
    }

}
