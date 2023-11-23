package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto);
    public List<?> getAnnouncements();
}
