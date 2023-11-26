package com.woofnmeow.wnm_project_back.dto.request;

import com.woofnmeow.wnm_project_back.entity.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditAnnouncementReqDto {
    private int announcement_id;
    private String title;
    private String content;
    private int isPinned;
    private String createDate;

    public Announcement toEntity(int announcementId) {
        return Announcement.builder()
                .announcement_id(announcementId)
                .title(title)
                .content(content)
                .isPinned(isPinned)
                .createDate(createDate)
                .build();
    }
}
