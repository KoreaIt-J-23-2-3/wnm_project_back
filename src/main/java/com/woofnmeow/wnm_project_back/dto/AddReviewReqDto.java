package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.Review;
import lombok.Data;

@Data
public class AddReviewReqDto {
    private int userId;
    private int productDtlId;
    private String reviewContent;
    private String reviewImgUrl;

    public Review toReviewEntity(int productMstId){
        return Review.builder()
                .userId(userId)
                .productMstId(productMstId)
                .productDtlId(productDtlId)
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .build();
    }

}
