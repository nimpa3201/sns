package com.example.sns.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "userFollows")
public class UserFollows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private User follow;
    public UserFollows() {

    }

}
