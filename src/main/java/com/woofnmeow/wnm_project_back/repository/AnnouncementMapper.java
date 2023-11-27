package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto);
    public List<Announcement> getAllAnnouncement();
    public int getAnnouncementCount();
    public Announcement getAnnouncementById(int announcementId);
    public int editAnnouncement(int announcementId, Announcement announcement);
    public int deleteAnnouncement(int announcementId);
}
