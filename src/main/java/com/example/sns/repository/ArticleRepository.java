package com.example.sns.repository;
import com.example.sns.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArticleRepository extends JpaRepository<Article, Long>  {
}
