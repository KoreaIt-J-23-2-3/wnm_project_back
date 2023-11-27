package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.EditUserReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetProductRespDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchUserReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserRespDto;
import com.woofnmeow.wnm_project_back.dto.request.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.exception.SignupException;
import com.woofnmeow.wnm_project_back.exception.UserException;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import com.woofnmeow.wnm_project_back.utils.UtilMapper;
import com.woofnmeow.wnm_project_back.utils.utilClass.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final ErrorMapper errorMapper;



    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReqDto signupReqDto) {
        try {
            return userMapper.saveUser(signupReqDto.toEntity()) > 0;
        }catch (Exception e) {
            throw new SignupException
                    (errorMapper.errorMapper("회원가입 오류", "회원가입 중 오류가 발생하였습니다."));
        }
    }







    // R
    public GetUserRespDto getUser(int userId) {
        try {
            return userMapper.findUserByUserId(userId).toRespDto();
        }catch (Exception e) {
            throw new UserException
                    (errorMapper.errorMapper("사용자 오류", "사용자 정보를 조회하는 중 오류가 발생하였습니다."));
        }
    }



    public List<GetUserRespDto> getUserList(SearchUserReqDto searchUserReqDto) {
        try{
            return userMapper.getUserList(searchUserReqDto.toVo())
                    .stream()
                    .map(User:: toRespDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
          throw new UserException
                  (errorMapper.errorMapper("사용자 오류", "사용자 정보를 조회하는 중 오류가 발생하였습니다."));
        }
    }

    public int getUserCount() {
        int result = 0;
        try {
            result = userMapper.getUserCount();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("사용자 수 조회 오류", "사용자 수를 조회하는 중 오류가 발생하였습니다.");
            throw new UserException(errorMap);
        }
        return result;
    }


  



  
    // U
    @Transactional(rollbackFor = Exception.class)
    public Boolean editUser(int userId, EditUserReqDto editUserReqDto) {
        try {
            return userMapper.editUser(editUserReqDto.toEntity(userId)) > 0;
        }catch (Exception e) {
            throw new UserException
                    (errorMapper.errorMapper("사용자 오류", "사용자 정보 수정 중 오류가 발생하였습니다."));
        }
    }







    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(int userId) {
        try {
            return userMapper.deleteUser(userId) > 0;
        }catch (Exception e) {
            throw new UserException
                    (errorMapper.errorMapper("사용자 오류", "사용자 정보 삭제 중 오류가 발생하였습니다."));
        }
    }



}
