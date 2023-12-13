package com.sparta.plusspring.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.plusspring.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserResponseDto extends CommonResponseDto{

    private String nickname;

    public UserResponseDto(String nickname) {
        this.nickname = getNickname();
    }

}
