package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.request.SearchUserReqDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.vo.SearchOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // C
    public Integer saveUser(User user);

    // R
    public User findUserByOauth2Id(String oauth2Id);
    public User findUserByUserId(int userId);
    public List<User> getUserList(SearchOrderVo searchOrderVo);

    // U
    public Integer editUser(User user);

    // D
    public int deleteUser(int userId);
}
