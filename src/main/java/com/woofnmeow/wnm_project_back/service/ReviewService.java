package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    // C
    @Transactional(rollbackFor = Exception.class)
    public boolean addReview(int productMstId, AddReviewReqDto addReviewReqDto) {
        return reviewMapper.addReview(addReviewReqDto.toReviewEntity(productMstId)) > 0;
    }

    // R


    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean editReview(int reviewId, EditReviewReqDto editReviewReqDto) {
        return reviewMapper.editReview(editReviewReqDto.toEditReviewEntity(reviewId)) > 0;
    }

    // D
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReview(int reviewId) {
        return reviewMapper.deleteReview(reviewId) > 0;
    }
}
