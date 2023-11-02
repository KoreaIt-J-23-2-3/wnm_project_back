package com.woofnmeow.wnm_project_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String roleName;
    private String oauth2Id;
    private String provider;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String defaultAddressNumber;
    private String defaultAddressName;
    private String defaultAddressDetailName;
    private String profileUrl;

}