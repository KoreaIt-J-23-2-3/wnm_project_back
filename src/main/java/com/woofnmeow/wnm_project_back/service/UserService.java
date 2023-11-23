package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.EditUserReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchUserReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserRespDto;
import com.woofnmeow.wnm_project_back.dto.request.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReqDto signupReqDto) {
        return userMapper.saveUser(signupReqDto.toEntity()) > 0;
    }

    // R
    public GetUserRespDto getUser(int userId) {
        return userMapper.findUserByUserId(userId).toRespDto();
    }

    public List<GetUserRespDto> getUserList(SearchUserReqDto searchUserReqDto) {
        return userMapper.getUserList(searchUserReqDto.toVo()).stream().map(User:: toRespDto).collect(Collectors.toList());
    }

    // U
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

    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(int userId) {
        return userMapper.deleteUser(userId) > 0;
    }

}
