package com.sparta.plusspring.comment.entity;

import com.sparta.plusspring.comment.dto.CommentRequest;
import com.sparta.plusspring.post.entity.Post;
import com.sparta.plusspring.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Comment(CommentRequest request, User user, Post post) {
        this.content = request.getContent();
        this.user = user;
        this.post = post;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
