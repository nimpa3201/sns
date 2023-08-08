package com.example.sns.entity;




import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Spring에 저장하고 싶은 사용자 정보

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String profileImg;
    private String email;
    private String phone;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<LikeArticle> likeArticle;

    @OneToMany(mappedBy = "follower")
    private List<UserFollows>userFollowerList;

    @OneToMany(mappedBy = "follow")
    private List<UserFollows> userFollowList;
    public User(){

    }

}