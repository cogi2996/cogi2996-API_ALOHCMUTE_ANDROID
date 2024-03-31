package com.example.social_media.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
@Data
public class PostDTO {
    private int postId;
    private String postText;
    private Instant postCreateTime;
    private String postImage;
    private int countLike;
    private UserDTO userDTO;

}
