package com.example.social_media.dao;

import com.example.social_media.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Integer> {
}
