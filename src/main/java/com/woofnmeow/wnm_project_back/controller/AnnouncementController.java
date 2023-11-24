package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllAnnouncementRespDto;
import com.woofnmeow.wnm_project_back.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/api/admin/announcement")
    public ResponseEntity<?> addAnnouncement(@RequestBody AddAnnouncementReqDto addAnnouncementReqDto) {
        return ResponseEntity.ok(announcementService.addAnnouncement(addAnnouncementReqDto));
    }
  
    @GetMapping("/api/allannouncement")
    public ResponseEntity<?> getAllAnnouncement() {
        List<GetAllAnnouncementRespDto> response =  announcementService.getAllAnnouncement();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/announcement/{announcementId}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable int announcementId) {
        System.out.println(announcementId);
        return ResponseEntity.ok(announcementService.getAnnouncementById(announcementId));
    }
}
