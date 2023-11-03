package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignupReqDto {
    @NotBlank
    private String oauth2Id;
    @NotBlank
    private String provider;
    @Email
    private String email;
    @Pattern(regexp = "^[가-힣]{2,6}$", message = "이름 형식을 다시 확인해주세요.")
    private String name;
    @Pattern(regexp = "^(?=[가-힣a-zA-Z0-9]*[a-z])(?=[가-힣a-zA-Z0-9]*[A-Z])[가-힣a-zA-Z0-9]{5,}$",
            message = "닉네임은 5자 이상 한글 / 영어 대,소문자 / 숫자만 사용가능합니다.")
    private String nickname;
    @Pattern(regexp = "^[0-9]{11}$", message = "전화번호를 다시 확인해주세요.")
    private String phoneNumber;
    @NotBlank
    private String defaultAddressNumber;
    @NotBlank
    private String defaultAddressName;
    @NotBlank
    private String defaultAddressDetailName;

    public User toEntity() {
        return User.builder()
                .oauth2Id(oauth2Id)
                .provider(provider)
                .email(email)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .defaultAddressNumber(defaultAddressNumber)
                .defaultAddressName(defaultAddressName)
                .defaultAddressDetailName(defaultAddressDetailName)
                .build();
    }


}
