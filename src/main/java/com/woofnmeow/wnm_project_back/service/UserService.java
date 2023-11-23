package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.EditUserReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetProductRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserRespDto;
import com.woofnmeow.wnm_project_back.dto.request.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.exception.SignupException;
import com.woofnmeow.wnm_project_back.exception.UserException;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReqDto signupReqDto) {
        boolean success = userMapper.saveUser(signupReqDto.toEntity()) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("회원가입 오류", "회원가입 중 오류가 발생하였습니다.");
            throw new SignupException(errorMap);
        }
        return success;
    }




    // R
    public GetUserRespDto getUser(int userId) {
        GetUserRespDto result = null;
        try {
            result = userMapper.findUserByUserId(userId).toRespDto();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("사용자 오류", "사용자 정보를 조회하는 중 오류가 발생하였습니다.");
            throw new UserException(errorMap);
        }
        return result;
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
        boolean success = userMapper.editUser(user) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("사용자 오류", "사용자 정보 수정 중 오류가 발생하였습니다.");
        }
        return success;
    }

    


    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(int userId) {
        boolean success = userMapper.deleteUser(userId) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("사용자 오류", "사용자 정보 삭제 중 오류가 발생하였습니다.");
            throw new UserException(errorMap);
        }
        return success;
    }

}
