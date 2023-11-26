package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementByIdRespDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Announcement {
    private int announcement_id;
    private String title;
    private String content;
    private int isPinned;
    private String createDate;

    public GetAllAnnouncementRespDto toGetAllAnnouncementRespDto() {
        return GetAllAnnouncementRespDto.builder()
                .announcement_id(announcement_id)
                .title(title)
                .content(content)
                .isPinned(isPinned)
                .createDate(createDate)
                .build();
    }

    public GetAnnouncementByIdRespDto toAnnouncementByIdRespDto() {
        return GetAnnouncementByIdRespDto.builder()
                .announcement_id(announcement_id)
                .title(title)
                .content(content)
                .isPinned(isPinned)
                .build();
    }
}
