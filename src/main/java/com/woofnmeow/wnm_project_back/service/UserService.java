package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.EditUserReqDto;
import com.woofnmeow.wnm_project_back.dto.GetUserRespDto;
import com.woofnmeow.wnm_project_back.dto.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReqDto signupReqDto) {
        return userMapper.saveUser(signupReqDto.toEntity()) > 0;
    }

    public GetUserRespDto getUser(int userId) {
        return userMapper.findUserByUserId(userId).toRespDto();
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean editUser(int userId, EditUserReqDto editUserReqDto) {

        User user = User.builder()
                .userId(userId)
                .nickname(editUserReqDto.getNickname())
                .phoneNumber(editUserReqDto.getPhoneNumber())
                .defaultAddressNumber(editUserReqDto.getDefaultAddressNumber())
                .defaultAddressName(editUserReqDto.getDefaultAddressName())
                .defaultAddressDetailName(editUserReqDto.getDefaultAddressDetailName())
                .profileUrl(editUserReqDto.getProfileUrl())
                .build();

        return userMapper.editUser(user) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(int userId) {
        return userMapper.deleteUser(userId) > 0;
    }


}
