package com.example.social_media.rest;

import com.example.social_media.DTO.ResponseDTO;
import com.example.social_media.entity.Department;
import com.example.social_media.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/department")
public class DepartmentRestController {
    private final DepartmentService departmentService;


    // find all department
    @GetMapping
    public ResponseEntity<?> getAllDepartment() {
        return ResponseEntity.ok(ResponseDTO.builder()
                .message("success")
                .data(departmentService.findAll())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
        Department department = departmentService.findDepartmentById(id);
        department.getMajors().stream().forEach(major -> System.out.println(major.getName()));
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }

    // get major by department id
    @GetMapping("/{id}/major")
    public ResponseEntity<?> getMajorByDepartmentId(@PathVariable int id) {
        Department department = departmentService.findDepartmentById(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                .message("success")
                .data(department.getMajors())
                .build());
    }




}
