package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAnnouncementByIdRespDto {
    private int announcement_id;
    private String title;
    private String content;
    private int isPinned;
    private String create_date;
}
