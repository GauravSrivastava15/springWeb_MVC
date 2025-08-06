package com.learning.springbootWebApplication.repositories;

import com.learning.springbootWebApplication.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> { // we pass the entity class and the type of the Primary Key of that entity
}
