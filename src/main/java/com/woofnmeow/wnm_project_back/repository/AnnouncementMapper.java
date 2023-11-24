package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementByIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto);
    public List<Announcement> getAllAnnouncement();

    public GetAnnouncementByIdRespDto getAnnouncementById(int announcementId);
}
