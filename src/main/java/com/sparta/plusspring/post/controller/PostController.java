package com.sparta.plusspring.post.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/posts")
@RestController
public class PostController {

    @GetMapping
    public ResponseEntity<Void> getPostList() {
        return ResponseEntity.ok().build();
    }
}
