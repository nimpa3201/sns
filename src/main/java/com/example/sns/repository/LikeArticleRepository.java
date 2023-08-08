package com.example.sns.repository;

import com.example.sns.entity.LikeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeArticleRepository extends JpaRepository<LikeArticle, Long> {
}
