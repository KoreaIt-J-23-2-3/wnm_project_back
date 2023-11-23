package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.repository.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementMapper announcementMapper;
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto) {
        return announcementMapper.addAnnouncement(addAnnouncementReqDto);
    }

    public List<?> addAnnouncement() {
        return announcementMapper.();
    }
}
