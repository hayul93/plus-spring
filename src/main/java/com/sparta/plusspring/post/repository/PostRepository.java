package com.sparta.plusspring.post.repository;

import com.sparta.plusspring.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
