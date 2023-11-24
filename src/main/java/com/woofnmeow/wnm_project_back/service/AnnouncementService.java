package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementByIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.exception.AnnouncementExcption;
import com.woofnmeow.wnm_project_back.repository.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementMapper announcementMapper;
  
    public boolean addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto) {
      boolean success = announcementMapper.addAnnouncement(addAnnouncementReqDto) > 0;
      if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 생성 중 오류가 발생하였습니다");
            throw new AnnouncementExcption(errorMap);
      }
      return success;
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
