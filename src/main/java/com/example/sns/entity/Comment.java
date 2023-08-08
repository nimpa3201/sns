package com.example.sns.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime deletedAt;



    @ManyToOne
    @JoinColumn(name = "articleId")
    Article article;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;


    public Comment(){

    }



}
