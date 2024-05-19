package com.example.social_media.DTO;

import lombok.Data;

import java.time.ZonedDateTime;


@Data
public class PostWithCheckLikeDTO {
    private int postId;
    private String postText;
    private ZonedDateTime postCreateTime;
    private String postImage;
    private int countLike;
    private boolean isLiked;
    private UserDTO userDTO;
}
