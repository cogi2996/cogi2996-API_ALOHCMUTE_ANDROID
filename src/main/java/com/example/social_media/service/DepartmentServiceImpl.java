package com.example.social_media.service;


import com.example.social_media.dao.DepartmentRepository;
import com.example.social_media.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    @Override
    public Department findDepartmentById(int id) {
        return departmentRepository.findDepartmentById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
