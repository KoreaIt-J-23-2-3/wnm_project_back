package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public Integer saveUser(User user);
}
