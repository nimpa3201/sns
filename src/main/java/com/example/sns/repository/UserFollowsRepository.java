package com.example.sns.repository;

import com.example.sns.entity.Comment;
import com.example.sns.entity.UserFollows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowsRepository extends JpaRepository<UserFollows, Long> {
}
