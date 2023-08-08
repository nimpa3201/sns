package com.example.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data

public class ArticleDTO {
    private Long id; // 댓글 id
    private String title;
    private String content;
    private LocalDateTime deletedat;




}
