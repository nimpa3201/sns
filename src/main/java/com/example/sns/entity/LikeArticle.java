package com.example.sns.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "likeArticle")
public class LikeArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
