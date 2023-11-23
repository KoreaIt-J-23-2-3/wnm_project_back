package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // C
    public Integer saveUser(User user);

    // R
    public User findUserByOauth2Id(String oauth2Id);
    public User findUserByUserId(int userId);

    // U
    public Integer editUser(User user);

    // D
    public int deleteUser(int userId);
}
