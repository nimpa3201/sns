package com.example.sns.repository;

import com.example.sns.entity.Article;
import com.example.sns.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
