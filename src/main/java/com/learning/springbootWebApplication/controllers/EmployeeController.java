package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import com.learning.springbootWebApplication.entities.EmployeeEntity;
import com.learning.springbootWebApplication.exceptions.ResourceNotFoundException;
import com.learning.springbootWebApplication.repositories.EmployeeRepository;
import com.learning.springbootWebApplication.services.EmployeeService;
import com.sun.net.httpserver.HttpServer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeID){
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(employeeID);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id " + employeeID));
    }



    @GetMapping
    public ResponseEntity<List<EmployeeDTO> >getAllEmployees(@RequestParam(required = false, name = "newAge") Integer age, @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployees){
        EmployeeDTO savedEmployeeDTO = employeeService.createNewEmployee(inputEmployees);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId){
         return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, updates);
    }

}
