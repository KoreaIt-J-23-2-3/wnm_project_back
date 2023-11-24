package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementByIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto);
    public List<Announcement> getAllAnnouncement();
    public GetAnnouncementByIdRespDto getAnnouncementById(int announcementId);
}
