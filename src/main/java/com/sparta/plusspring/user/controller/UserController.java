package com.sparta.plusspring.user.controller;

import com.sparta.plusspring.user.dto.CommonResponseDto;
import com.sparta.plusspring.user.dto.UserRequestDto;
import com.sparta.plusspring.user.dto.UserResponseDto;
import com.sparta.plusspring.user.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        try {
            userService.signup(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @GetMapping("/signup/check")
    public ResponseEntity<?> nameCheck(
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        try {
            userService.nameCheck(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("사용할 수 있는 닉네임입니다.", HttpStatus.CREATED.value()));
    }

}
