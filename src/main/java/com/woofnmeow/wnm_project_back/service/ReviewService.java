package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByProductMstIdRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByUserIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Review;
import com.woofnmeow.wnm_project_back.exception.ReviewException;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    // C
    @Transactional(rollbackFor = Exception.class)
    public boolean addReview(AddReviewReqDto addReviewReqDto) {
        boolean success = reviewMapper.addReview(addReviewReqDto.toReviewEntity()) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("리뷰 오류", "리뷰 추가 중 오류가 발행하였습니다");
            throw new ReviewException(errorMap);
        }
        return success;
    }




    // R
    public List<GetReviewByProductMstIdRespDto> getReviewsByProductMstId(int productMstId) {
        List<Review> result = new ArrayList<>();
        try {
            result = reviewMapper.selectReviewsByProductMstId(productMstId);

        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("리뷰 오류", "리뷰를 불러오는 중 오류가 발생하였습니다.");
            throw new ReviewException(errorMap);
        }
        return result.stream().map(Review::toDetailPageReviewResponseDto).collect(Collectors.toList());
    }

    public List<GetReviewByUserIdRespDto> getReviewsByUserId(int userId) {
        List<Review> result = new ArrayList<>();
        try {
            result = reviewMapper.selectReviewsByUserId(userId);
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("리뷰 오류", "리뷰를 불러오는 중 오류가 발생하였습니다.");
            throw new ReviewException(errorMap);
        }
        return result.stream().map(Review::toMypageReviewResponseDto).collect(Collectors.toList());
    }




    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean editReview(int reviewId, EditReviewReqDto editReviewReqDto) {
        boolean success = reviewMapper.editReview(editReviewReqDto.toEditReviewEntity(reviewId)) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("리뷰 오류", "리뷰 수정 중 오류가 발생하였습니다.");
            throw new ReviewException(errorMap);
        }
        return success;
    }




    // D
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReview(int reviewId) {
        boolean success = reviewMapper.deleteReview(reviewId) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("리뷰 오류", "리뷰 삭제 중 오류가 발생하였습니다.");
            throw new ReviewException(errorMap);
        }
        return success;
    }



}
