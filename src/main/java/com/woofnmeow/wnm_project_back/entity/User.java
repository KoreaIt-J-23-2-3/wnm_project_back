package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetUserRespDto;
import com.woofnmeow.wnm_project_back.dto.PrincipalRespDto;
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

    public GetUserRespDto toRespDto() {
        return GetUserRespDto.builder()
                .userId(userId)
                .roleName(roleName)
                .oauth2Id(oauth2Id)
                .provider(provider)
                .name(name)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .defaultAddressNumber(defaultAddressNumber)
                .defaultAddressName(defaultAddressName)
                .defaultAddressDetailName(defaultAddressDetailName)
                .profileUrl(profileUrl)
                .build();
    }

    public PrincipalRespDto toPrincipalRespDto() {
        return PrincipalRespDto.builder()
                .userId(userId)
                .roleName(roleName)
                .oauth2Id(oauth2Id)
                .provider(provider)
                .name(name)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .defaultAddressNumber(defaultAddressNumber)
                .defaultAddressName(defaultAddressName)
                .defaultAddressDetailName(defaultAddressDetailName)
                .profileUrl(profileUrl)
                .build();
    }
}