package com.woofnmeow.wnm_project_back.dto;

import lombok.Data;

@Data
public class AddAnnouncementReqDto {
    private String title;
    private String content;
    private String type;
}
