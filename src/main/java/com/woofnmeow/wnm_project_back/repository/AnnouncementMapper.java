package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto);
}
