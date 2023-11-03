package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public Integer saveUser(User user);

    public User findUserByOauth2Id(String oauth2Id);
    public User findUserByUserId(int userId);

    public Integer editUser(User user);

    public int deleteUser(int userId);
}
