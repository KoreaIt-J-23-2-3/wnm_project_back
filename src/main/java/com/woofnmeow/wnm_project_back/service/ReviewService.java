package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.Review;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addReview(int productId, AddReviewReqDto addReviewReqDto) {
        return reviewMapper.addReview(addReviewReqDto.toReviewEntity(productId)) > 0;
    }
}
