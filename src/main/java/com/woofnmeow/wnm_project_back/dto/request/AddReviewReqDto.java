package com.woofnmeow.wnm_project_back.dto.request;

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


                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .build();
    }

}
