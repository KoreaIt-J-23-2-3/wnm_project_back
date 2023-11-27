package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementRespDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Announcement {
    private int announcementId;
    private String title;
    private String content;
    private int isPinned;
    private String createDate;

    public GetAnnouncementRespDto toGetAnnouncementRespDto() {
        return GetAnnouncementRespDto.builder()
                .announcementId(announcementId)
                .title(title)
                .content(content)
                .isPinned(isPinned)
                .createDate(createDate)
                .build();
    }

}
