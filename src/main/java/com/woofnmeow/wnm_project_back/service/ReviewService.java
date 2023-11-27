package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditReviewReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByProductMstIdRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByUserIdRespDto;
import com.woofnmeow.wnm_project_back.entity.Review;
import com.woofnmeow.wnm_project_back.exception.ReviewException;
import com.woofnmeow.wnm_project_back.repository.ReviewMapper;
import com.woofnmeow.wnm_project_back.utils.utilClass.ErrorMapper;
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
    private final ErrorMapper errorMapper;

    // C
    @Transactional(rollbackFor = Exception.class)
    public boolean addReview(AddReviewReqDto addReviewReqDto) {
        try {
            return reviewMapper.addReview(addReviewReqDto.toReviewEntity()) > 0;
        }catch (Exception e) {
            throw new ReviewException
                    (errorMapper.errorMapper("리뷰 오류", "리뷰 추가 중 오류가 발행하였습니다"));
        }
    }





    // R
    public List<GetReviewByProductMstIdRespDto> getReviewsByProductMstId(int productMstId) {
        try {
            return reviewMapper.selectReviewsByProductMstId(productMstId)
                    .stream()
                    .map(Review::toDetailPageReviewResponseDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new ReviewException
                    (errorMapper.errorMapper("리뷰 오류", "리뷰를 불러오는 중 오류가 발생하였습니다."));
        }
    }





    public List<GetReviewByUserIdRespDto> getReviewsByUserId(int userId) {
        try {
            return reviewMapper.selectReviewsByUserId(userId)
                    .stream()
                    .map(Review::toMypageReviewResponseDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new ReviewException
                    (errorMapper.errorMapper("리뷰 오류", "리뷰를 불러오는 중 오류가 발생하였습니다."));
        }
    }





    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean editReview(int reviewId, EditReviewReqDto editReviewReqDto) {
        try {
            return reviewMapper.editReview(editReviewReqDto.toEditReviewEntity(reviewId)) > 0;
        }catch (Exception e) {
            throw new ReviewException
                    (errorMapper.errorMapper("리뷰 오류", "리뷰 수정 중 오류가 발생하였습니다."));
        }
    }





    // D
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReview(int reviewId) {
        try {
            return reviewMapper.deleteReview(reviewId) > 0;
        }catch (Exception e) {
            throw new ReviewException
                    (errorMapper.errorMapper("리뷰 오류", "리뷰 삭제 중 오류가 발생하였습니다."));
        }
    }




}
