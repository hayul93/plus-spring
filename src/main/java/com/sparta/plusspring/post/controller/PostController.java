package com.sparta.plusspring.post.controller;


import com.sparta.plusspring.CommonResponseDto;
import com.sparta.plusspring.post.dto.PostRequestDto;
import com.sparta.plusspring.post.dto.PostResponseDto;
import com.sparta.plusspring.post.service.PostService;
import com.sparta.plusspring.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    //게시글 전제 조회 - 페이징 처리
    @GetMapping("/list")
    public Page<PostResponseDto> getAllPosts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getAllPosts(pageable);
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

    //게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto, userDetails);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            postService.deletePost(postId, userDetails);
            return ResponseEntity.ok().body(new CommonResponseDto("정상적으로 삭제 되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


}
