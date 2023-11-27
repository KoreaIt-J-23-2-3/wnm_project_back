package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.entity.Announcement;
import com.woofnmeow.wnm_project_back.exception.AnnouncementExcption;
import com.woofnmeow.wnm_project_back.repository.AnnouncementMapper;
import com.woofnmeow.wnm_project_back.utils.utilClass.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementMapper announcementMapper;
    private final ErrorMapper errorMapper;


    // C
    public boolean addAnnouncement(AddAnnouncementReqDto addAnnouncementReqDto) {
        try {
            return announcementMapper.addAnnouncement(addAnnouncementReqDto) > 0;
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 생성 중 오류가 발생하였습니다"));
        }
    }








    // R
    public List<GetAnnouncementRespDto> getAllAnnouncement() {
        try {
            return announcementMapper.getAllAnnouncement()
                    .stream()
                    .map(Announcement::toGetAnnouncementRespDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 조회 중 오류가 발생하였습니다."));
        }
    }


    
    public int getAnnouncementCount() {
        try {
            return announcementMapper.getAnnouncementCount();
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 갯수 조회 중 오류가 발생하였습니다."));
        }
    }



    
    public GetAnnouncementRespDto getAnnouncementById(int announcementId) {
        try {
            return announcementMapper.getAnnouncementById(announcementId).toGetAnnouncementRespDto();
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 조회 중 오류가 발생하였습니다."));
        }
    }











    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean editAnnouncement(int announcementId, EditAnnouncementReqDto editAnnouncementReqDto) {
        try {
            return announcementMapper
                    .editAnnouncement(announcementId, editAnnouncementReqDto.toEntity(announcementId)) > 0;
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 수정 중 오류가 발생하였습니다."));
        }

    }












    // D
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAnnouncement(int announcementId) {
        try {
            return announcementMapper.deleteAnnouncement(announcementId) > 0;
        }catch (Exception e) {
            throw new AnnouncementExcption
                    (errorMapper.errorMapper("공지사항", "공지사항 삭제 중 오류가 발생하였습니다."));
        }
    }




}
