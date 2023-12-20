package com.sparta.plusspring.post.dto;

import com.sparta.plusspring.comment.dto.CommentResponse;
import com.sparta.plusspring.comment.entity.Comment;
import com.sparta.plusspring.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createDate;
    private List<CommentResponse> commentList;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getUser().getNickname();
        this.createDate = post.getCreateDate();
    }

    public PostResponseDto(Post post, List<Comment> commentList, String nickname) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createDate = post.getCreateDate();
        this.commentList = new ArrayList<>();
        for (Comment comment : commentList) {
            this.commentList.add(new CommentResponse(comment));
        }
        this.nickname = nickname;
    }
}
