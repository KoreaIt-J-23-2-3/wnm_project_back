package com.woofnmeow.wnm_project_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddAnnouncementReqDto {
    private String title;
    private String content;
    private String type;
    private int isPinned;
}
