package com.example.social_media.rest;

import com.example.social_media.entity.NotifyUniversity;
import com.example.social_media.service.NotifyUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification/university")
@RequiredArgsConstructor
public class NotifyUniversityRestController {
    private final NotifyUniversityService notifyUniversityService;

    @GetMapping
    public ResponseEntity<List<NotifyUniversity>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(notifyUniversityService.findAll(pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotifyUniversity> findById(@PathVariable int id) {
        return ResponseEntity.ok(notifyUniversityService.findById(id));
    }
}
