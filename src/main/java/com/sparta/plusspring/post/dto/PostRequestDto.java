package com.sparta.plusspring.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    @Size(max = 500, message = "500자 이내로 입력해주세요")
    private String title;

    @Size(max = 500, message = "5000자 이내로 입력해주세요")
    private String content;
}
