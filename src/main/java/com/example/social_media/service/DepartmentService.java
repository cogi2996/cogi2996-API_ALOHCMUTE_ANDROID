package com.example.social_media.service;

import com.example.social_media.dao.DepartmentRepository;
import com.example.social_media.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
     Department findDepartmentById(int id);

     List<Department> findAll();

}
