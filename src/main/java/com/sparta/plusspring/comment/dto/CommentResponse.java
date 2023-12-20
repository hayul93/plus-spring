package com.sparta.plusspring.comment.dto;

import com.sparta.plusspring.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private String content;
    private String nickname;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
    }
}
