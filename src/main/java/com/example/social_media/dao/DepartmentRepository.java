package com.example.social_media.dao;

import com.example.social_media.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // find major by department id
    Department findDepartmentById(int id);

}
