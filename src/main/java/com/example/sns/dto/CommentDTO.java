package com.example.sns.dto;

import com.example.sns.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data

public class CommentDTO {
    private Long id; // 댓글 id

    private String content;


    public static CommentDTO fromEntity(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .build();
    }
}
