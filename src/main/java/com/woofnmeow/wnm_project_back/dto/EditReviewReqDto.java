package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Review;
import lombok.Data;

@Data
public class EditReviewReqDto {
    private String reviewContent;
    private String reviewImgUrl;

    public Review toEditReviewEntity(int reviewId) {
        return Review.builder()
                .reviewId(reviewId)
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .build();
    }
}
