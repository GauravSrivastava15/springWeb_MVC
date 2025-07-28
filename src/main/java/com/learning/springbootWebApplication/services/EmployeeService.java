package com.learning.springbootWebApplication.services;

import com.learning.springbootWebApplication.dto.EmployeeDTO;
import com.learning.springbootWebApplication.entities.EmployeeEntity;
import com.learning.springbootWebApplication.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long employeeID) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(employeeID).orElse(null);

        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployees) {
        EmployeeEntity toSave = modelMapper.map(inputEmployees, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity =  employeeRepository.save(toSave);  //Repo can only accepts employee entity so we should send entity or dto
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}
