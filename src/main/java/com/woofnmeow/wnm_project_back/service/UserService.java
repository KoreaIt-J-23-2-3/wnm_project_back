package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.SignupReqDto;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public boolean signup(SignupReqDto signupReqDto) {
        return userMapper.saveUser(signupReqDto.toEntity()) > 0;
    }
}
