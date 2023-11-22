package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import com.woofnmeow.wnm_project_back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/review/{productMstId}")
    public ResponseEntity<?> addReview(@PathVariable int productMstId, @RequestBody AddReviewReqDto addReviewReqDto) {

        return ResponseEntity.ok().body(reviewService.addReview(productMstId, addReviewReqDto));
    }

}
