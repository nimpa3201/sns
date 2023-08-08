package com.example.sns.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "article")

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String title;

    private String content;
    //private boolean draft;
    private LocalDateTime deletedAt;


    @OneToMany(mappedBy = "article")
    private List<ArticleImage> articleImages;
    @OneToMany(mappedBy = "article")
    private List<LikeArticle> likeArticles;
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    public Article() {

    }
}
