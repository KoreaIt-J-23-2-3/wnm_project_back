package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
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

    public GetAllAnnouncementRespDto toRespDto() {
        return GetAllAnnouncementRespDto.builder()
                .announcement_id(announcement_id)
                .title(title)
                .content(content)
                .isPinned(isPinned)
                .createDate(createDate)
                .build();
    }
}
