package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.SignupReqDto;
import com.woofnmeow.wnm_project_back.entity.Review;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addReview(int productMstId, AddReviewReqDto addReviewReqDto) {
        return reviewMapper.addReview(addReviewReqDto.toReviewEntity(productMstId)) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editReview(int reviewId, EditReviewReqDto editReviewReqDto) {
        return reviewMapper.editReview(editReviewReqDto.toEditReviewEntity(reviewId)) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReview(int reviewId) {
        return reviewMapper.deleteReview(reviewId) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Review> getReviewsByProductMstId(int productMstId) {
        return reviewMapper.selectReviewsByProductMstId(productMstId);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Review> getReviewsByUserId(int userId) {
        return reviewMapper.selectReviewsByUserId(userId);
    }
}
