package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import com.woofnmeow.wnm_project_back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/review/{productMstId}")
    public ResponseEntity<?> addReview(@PathVariable int productMstId, @RequestBody AddReviewReqDto addReviewReqDto) {
        return ResponseEntity.ok().body(reviewService.addReview(productMstId, addReviewReqDto));
    }

    @PutMapping("api/review/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable int reviewId, @RequestBody EditReviewReqDto editReviewReqDto) {
        return ResponseEntity.ok().body(reviewService.editReview(reviewId, editReviewReqDto));
    }

    @DeleteMapping("/api/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }

}
