package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Data;

@Data
public class GetAnnouncementByIdRespDto {
    private int announcement_id;
    private String title;
    private String content;
    private String create_date;
}
