package com.sparta.plusspring.user.service;

import com.sparta.plusspring.jwt.JwtUtil;
import com.sparta.plusspring.user.dto.UserRequestDto;
import com.sparta.plusspring.user.entity.User;
import com.sparta.plusspring.user.repository.UserRepository;
import com.sparta.plusspring.user.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void signup(UserRequestDto userRequestDto) {
        String nickname = userRequestDto.getNickname();
        String password = userRequestDto.getPassword();
        String passwordCheck = userRequestDto.getPasswordCheck();
        String passwordUse = passwordEncoder.encode(userRequestDto.getPassword());

        //즁복유저 확인 //유저닉네임, 비밀번호 중복확인
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 사용된 닉네임입니다.");
        } else if (nickname.equals(password)) {
            throw new IllegalArgumentException("닉네임과 비밀번호가 일치합니다. 다른 비밀번호를 입력해주세요.");
        } else if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        User user = new User(nickname, passwordUse);

        userRepository.save(user);
    }

    public void nameCheck(UserRequestDto userRequestDto) {
        String nickname = userRequestDto.getNickname();

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
    }

    public void login(UserRequestDto userRequestDto, HttpServletResponse res) {
        String nickname = userRequestDto.getNickname();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //jwt 생성 및 쿠키에 저장
        String token = jwtUtil.createToken(user.getNickname());
        jwtUtil.addJwtToCookie(token, res);
    }
}
