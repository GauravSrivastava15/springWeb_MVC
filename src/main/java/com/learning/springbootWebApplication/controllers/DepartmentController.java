package com.learning.springbootWebApplication.controllers;

import com.learning.springbootWebApplication.dto.DepartmentDTO;
import com.learning.springbootWebApplication.exceptions.ResourceNotFoundException;
import com.learning.springbootWebApplication.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("/{departmentID}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer departmentID){
        Optional<DepartmentDTO> department = departmentService.getDepartmentById(departmentID);
        return department
                .map(departmentDTO -> ResponseEntity.ok(departmentDTO))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentID));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentDTO data){
        DepartmentDTO newDepartment =  departmentService.addDepartment(data);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }
}
