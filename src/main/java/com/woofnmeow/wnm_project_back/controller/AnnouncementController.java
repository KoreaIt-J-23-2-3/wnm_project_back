package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddAnnouncementReqDto;
import com.woofnmeow.wnm_project_back.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/api/announcement")
    public ResponseEntity<?> addAnnouncement(@RequestBody AddAnnouncementReqDto addAnnouncementReqDto) {
        System.out.println(addAnnouncementReqDto);
        return ResponseEntity.ok(announcementService.addAnnouncement(addAnnouncementReqDto));
    }
}
