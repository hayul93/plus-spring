package com.sparta.plusspring.post.entity;

import com.sparta.plusspring.post.dto.PostRequestDto;
import com.sparta.plusspring.user.entity.User;
import com.sparta.plusspring.user.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity(name = "Posts")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    public Post(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.createDate = LocalDateTime.now();
        this.user = userDetails.getUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

}
