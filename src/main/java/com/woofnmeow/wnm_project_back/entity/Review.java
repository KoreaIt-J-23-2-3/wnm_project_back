package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetReviewByProductMstIdRespDto;
import com.woofnmeow.wnm_project_back.dto.GetReviewByUserIdRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private int reviewId;
    private int userId;
    private int productMstId;
    private int productDtlId;
    private String reviewContent;
    private String reviewImgUrl;
    private LocalDate reviewDate;

    public GetReviewByProductMstIdRespDto toMypageReviewResponseDto(String nickname) {
        return GetReviewByProductMstIdRespDto.builder()
                .nickname(nickname)
                .productDtlId(productDtlId)
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate)
                .build();
    }

    public GetReviewByUserIdRespDto toDetailPageReviewResponseDto() {
        return GetReviewByUserIdRespDto.builder()
                .productMstId(productMstId)
                .productDtlId(productDtlId)
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate)
                .build();
    }
}
