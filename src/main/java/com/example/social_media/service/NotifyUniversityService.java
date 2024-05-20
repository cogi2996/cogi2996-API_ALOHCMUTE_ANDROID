package com.example.social_media.service;

import com.example.social_media.entity.NotifyUniversity;

import java.util.List;
import java.util.Optional;

public interface NotifyUniversityService {
    List<NotifyUniversity> findAll(int pageNum, int pageSize);
    NotifyUniversity findById(int id);
}
