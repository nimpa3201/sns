package com.example.sns.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articleImage")
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "ArticleId")
    Article article;
    public ArticleImage() {

    }

}
