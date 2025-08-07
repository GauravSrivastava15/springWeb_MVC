package com.learning.springbootWebApplication.services;

import com.learning.springbootWebApplication.dto.DepartmentDTO;
import com.learning.springbootWebApplication.entities.DepartmentEntity;
import com.learning.springbootWebApplication.exceptions.ResourceNotFoundException;
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

    public DepartmentDTO updateDepartmentById(@Valid DepartmentDTO departmentDTO, Integer departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity updatedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedEntity, DepartmentDTO.class);
    }

        // another way to update the department by getting the department and setting different properties
//    public DepartmentDTO updateDepartmentById(@Valid DepartmentDTO departmentDTO, Integer departmentId) {
//        DepartmentEntity existing = departmentRepository.findById(departmentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));
//
//        // Only update fields (not ID)
//        existing.setTitle(departmentDTO.getTitle());
//        existing.setIsActive(departmentDTO.getIsActive());
//        existing.setCreatedAt(departmentDTO.getCreatedAt());
//
//        DepartmentEntity updated = departmentRepository.save(existing);
//        return modelMapper.map(updated, DepartmentDTO.class);
//    }


    public void deleteDepartmentById(Integer departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        departmentRepository.deleteById(departmentId);
    }
}
