package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.entity.Review;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import com.woofnmeow.wnm_project_back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // C
    @PostMapping("/api/review")
    public ResponseEntity<?> addReview(@RequestBody AddReviewReqDto addReviewReqDto) {
        return ResponseEntity.ok().body(reviewService.addReview(addReviewReqDto));
    }

  
  
  
    // R
    @GetMapping("/api/reviews/product/{productMstId}")
    public ResponseEntity<?> getProductReviews(@PathVariable int productMstId) {
        System.out.println(productMstId);
        return ResponseEntity.ok().body(reviewService.getReviewsByProductMstId(productMstId));
    }

    @GetMapping("/api/reviews/user/{userId}")
    public ResponseEntity<?> getUserReviews(@PathVariable int userId) {
        System.out.println(userId);
        return ResponseEntity.ok().body(reviewService.getReviewsByUserId(userId));
    }

  
  
  
    // U
    @PutMapping("api/review/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable int reviewId, @RequestBody EditReviewReqDto editReviewReqDto) {
        return ResponseEntity.ok().body(reviewService.editReview(reviewId, editReviewReqDto));
    }

  
  
  
    // D
    @DeleteMapping("/api/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }


}
