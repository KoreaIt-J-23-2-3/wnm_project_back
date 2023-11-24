package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetReviewByProductMstIdRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByUserIdRespDto;
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
    private String reviewContent;
    private String reviewImgUrl;
    private LocalDate reviewDate;

    private Order order;

    public GetReviewByProductMstIdRespDto toMypageReviewResponseDto(String nickname, String profileUrl) {
        return GetReviewByProductMstIdRespDto.builder()
                .profileUrl(profileUrl)
                .nickname(nickname)

                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate)
                .build();
    }

    public GetReviewByUserIdRespDto toDetailPageReviewResponseDto() {
        return GetReviewByUserIdRespDto.builder()
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate)
                .build();
    }
}
