package com.sparta.plusspring.user.service;

import com.sparta.plusspring.user.dto.UserRequestDto;
import com.sparta.plusspring.user.entity.User;
import com.sparta.plusspring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String nickname = userRequestDto.getNickname();
        String password = userRequestDto.getPassword();

        //즁복유저 확인 //유저닉네임 비밀번호 중복확인
        if (nickname.equals(password)) {
            throw new IllegalArgumentException("닉네임과 비밀번호가 일치합니다. 다른 비밀번호를 입력해주세요.");
        } else if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(nickname, password);

        userRepository.save(user);
    }

}
