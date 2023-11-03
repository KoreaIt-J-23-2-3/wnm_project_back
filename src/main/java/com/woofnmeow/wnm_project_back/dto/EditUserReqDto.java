package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EditUserReqDto {

    @NotBlank
    private String nickname;
    @Pattern(regexp = "^[0-9]{11}$", message = "전화번호를 다시 확인해주세요.")
    private String phoneNumber;
    @NotBlank
    private String defaultAddressNumber;
    @NotBlank
    private String defaultAddressName;
    @NotBlank
    private String defaultAddressDetailName;
    private String profileUrl;
}
