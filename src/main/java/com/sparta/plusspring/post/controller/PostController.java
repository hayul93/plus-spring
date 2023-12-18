package com.sparta.plusspring.post.controller;


import com.sparta.plusspring.post.dto.PostRequestDto;
import com.sparta.plusspring.post.dto.PostResponseDto;
import com.sparta.plusspring.post.service.PostService;
import com.sparta.plusspring.CommonResponseDto;
import com.sparta.plusspring.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<?> createPosts(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        try {
            PostResponseDto postResponseDto = postService.createPosts(postRequestDto, userDetails);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //전체 게시글 조회
    @GetMapping("/list")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    //단건 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        try {
            PostResponseDto postResponseDto = postService.getPostDto(postId);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }





}
