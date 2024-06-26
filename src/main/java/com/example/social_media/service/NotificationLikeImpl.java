package com.example.social_media.service;

import com.example.social_media.dao.NotificationLikeRepository;
import com.example.social_media.entity.NotificationLikePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationLikeImpl implements NotificationLikeService {
    private final NotificationLikeRepository announceLikeRepository;

    @Override
    public void save(NotificationLikePost notificationLikePost) {
        announceLikeRepository.save(notificationLikePost);
    }

    @Override
    public void delete(NotificationLikePost notificationLikePost) {
        announceLikeRepository.delete(notificationLikePost);
    }

    @Override
    public NotificationLikePost findByUserLikedId(int userId, int postId) {
        return announceLikeRepository.findByUserLikedId(userId, postId);
    }
}
