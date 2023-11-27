package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.exception.AnnouncementExcption;
import com.woofnmeow.wnm_project_back.repository.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<GetAnnouncementRespDto> getAllAnnouncement() {
        List<Announcement> list = announcementMapper.getAllAnnouncement();
        List<GetAnnouncementRespDto> respList = new ArrayList<>();
        try {
            list.forEach(ann -> {
                respList.add(ann.toGetAnnouncementRespDto());
            });
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 조회 중 오류가 발생하였습니다.");
            throw new AnnouncementExcption(errorMap);
        }
        return respList;
    }

    public int getAnnouncementCount() {
        int count = 0;
        try {
            count = announcementMapper.getAnnouncementCount();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 갯수 조회 중 오류가 발생하였습니다.");
            throw new AnnouncementExcption(errorMap);
        }
        return count;
    }

    public GetAnnouncementRespDto getAnnouncementById(int announcementId) {
            GetAnnouncementRespDto RespDto = null;
        try {
            RespDto = announcementMapper.getAnnouncementById(announcementId).toGetAnnouncementRespDto();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 조회 중 오류가 발생하였습니다.");
            throw new AnnouncementExcption(errorMap);
        }
        return RespDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editAnnouncement(int announcementId, EditAnnouncementReqDto editAnnouncementReqDto) {
        Announcement announcement = null;
        try {
            announcement = editAnnouncementReqDto.toEntity(announcementId);
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 수정 중 오류가 발생하였습니다.");
            throw new AnnouncementExcption(errorMap);
        }
        return announcementMapper.editAnnouncement(announcementId, announcement) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAnnouncement(int announcementId) {
        boolean success = false;
        try {
            success = announcementMapper.deleteAnnouncement(announcementId) > 0;
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("공지사항", "공지사항 삭제 중 오류가 발생하였습니다.");
            throw new AnnouncementExcption(errorMap);
        }
        return success;
    }
}
