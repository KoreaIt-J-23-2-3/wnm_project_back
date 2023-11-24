package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementByIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.repository.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementMapper announcementMapper;
    public int addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto) {
        return announcementMapper.addAnnouncement(addAnnouncementReqDto);
    }

    public List<GetAllAnnouncementRespDto> getAllAnnouncement() {
        List<Announcement> list = announcementMapper.getAllAnnouncement();
        List<GetAllAnnouncementRespDto> respList = new ArrayList<>();
        list.forEach(ann -> {
            respList.add(ann.toRespDto());
        });
        return respList;
    }

    public GetAnnouncementByIdRespDto getAnnouncementById(int announcementId) {
        return announcementMapper.getAnnouncementById(announcementId);
    }
}
