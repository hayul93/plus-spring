package com.sparta.plusspring.user.dto;

import com.sparta.plusspring.CommonResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto extends CommonResponseDto {

    private String nickname;
    private String password;
}
