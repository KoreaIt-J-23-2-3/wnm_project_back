package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAllAnnouncementRespDto {
    private int announcement_id;
    private String title;
    private String content;
    private int isPinned;
    private String createDate;
}
