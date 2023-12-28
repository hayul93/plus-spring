package com.sparta.plusspring.post.repository;

import com.sparta.plusspring.post.dto.PostResponseDto;
import com.sparta.plusspring.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<PostResponseDto> findByTitleContaining(String searchKeyword, Pageable pageable);

}
