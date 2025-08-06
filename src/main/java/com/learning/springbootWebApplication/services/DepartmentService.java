package com.learning.springbootWebApplication.services;

import com.learning.springbootWebApplication.dto.DepartmentDTO;
import com.learning.springbootWebApplication.entities.DepartmentEntity;
import com.learning.springbootWebApplication.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public DepartmentDTO addDepartment(@Valid DepartmentDTO data) {
        DepartmentEntity dataToSave = modelMapper.map(data, DepartmentEntity.class);
        DepartmentEntity savedData = departmentRepository.save(dataToSave);
        return modelMapper.map(savedData, DepartmentDTO.class);
    }
}
