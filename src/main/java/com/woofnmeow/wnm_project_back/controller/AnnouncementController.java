package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/api/admin/announcement")
    public ResponseEntity<?> addAnnouncement(@RequestBody AddAnnouncementReqDto addAnnouncementReqDto) {
        return ResponseEntity.ok(announcementService.addAnnouncement(addAnnouncementReqDto));
    }
  
    @GetMapping("/api/announcement/all")
    public ResponseEntity<?> getAllAnnouncement() {
        List<GetAllAnnouncementRespDto> response =  announcementService.getAllAnnouncement();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/announcement/{announcementId}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable int announcementId) {
        return ResponseEntity.ok(announcementService.getAnnouncementById(announcementId));
    }

    @PutMapping("/api/admin/announcement/{announcementId}")
    public ResponseEntity<?> editAnnouncement(@PathVariable int announcementId,@RequestBody EditAnnouncementReqDto editAnnouncementReqDto) {
        return ResponseEntity.ok(announcementService.editAnnouncement(announcementId, editAnnouncementReqDto));
    }

    @DeleteMapping("/api/admin/announcement/{announcementId}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable int announcementId) {
        return ResponseEntity.ok(announcementService.deleteAnnouncement(announcementId));
    }
}
