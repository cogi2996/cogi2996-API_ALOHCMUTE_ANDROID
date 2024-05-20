package com.example.social_media.dao;

import com.example.social_media.entity.NotifyUniversity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotifyUniversityRepository extends JpaRepository<NotifyUniversity, Integer>{
    // findAll
    @Query("select n from NotifyUniversity n")
    List <NotifyUniversity> findAllNoti(Pageable pageable);
    // findById

}
