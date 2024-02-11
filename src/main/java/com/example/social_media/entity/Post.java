package com.example.social_media.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private int postId;
    @Column(name="group_id")
    private int groupId;
    @Column(name="user_id")
    private String userId;
    @Column(name="post_text")
    private String postText;
    @Column(name="post_create_time")
    private Date postCreateTime;
    @Column(name="post_image")
    private String postImage;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User userGroup;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group groupPost;


}
