package com.sparta.plusspring.comment.dto;

import com.sparta.plusspring.post.entity.Post;
import lombok.Getter;

@Getter
public class CommentRequest {
    private Long id;
    private String content;
    private Post post;
}
