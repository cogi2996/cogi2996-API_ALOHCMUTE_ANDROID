package com.example.social_media.service;

import com.example.social_media.dao.NotifyUniversityRepository;
import com.example.social_media.entity.NotifyUniversity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class NotifyUniversityServiceImpl implements NotifyUniversityService{
    private final NotifyUniversityRepository notifyUniversityRepository;
    @Override
    public List<NotifyUniversity> findAll(int pageNum, int pageSize) {
        return notifyUniversityRepository.findAllNoti(Pageable.ofSize(pageSize).withPage(pageNum));
    }

    @Override
    public NotifyUniversity findById(int id) {
        return notifyUniversityRepository.findById(id).orElse(null);
    }
}
