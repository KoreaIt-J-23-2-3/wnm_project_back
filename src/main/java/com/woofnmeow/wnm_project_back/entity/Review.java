package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetReviewByProductMstIdRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetReviewByUserIdRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private int reviewId;
    private int orderProductsId;
    private String reviewContent;
    private String reviewImgUrl;
    private LocalDate reviewDate;

    private OrderProducts orderProducts;

    public GetReviewByProductMstIdRespDto toDetailPageReviewResponseDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return GetReviewByProductMstIdRespDto.builder()
                .profileUrl(orderProducts.getOrder().getUser().getProfileUrl())
                .nickname(orderProducts.getOrder().getUser().getNickname())
                .sizeName(orderProducts.getProductDtl().getSize().getSizeName())
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate.format(formatter))
                .build();
    }


    public GetReviewByUserIdRespDto toMypageReviewResponseDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return GetReviewByUserIdRespDto.builder()
                .reviewId(reviewId)
                .sizeName(orderProducts.getProductDtl().getSize().getSizeName())
                .productName(orderProducts.getProductDtl().getProductMst().getProductName())
                .reviewContent(reviewContent)
                .reviewImgUrl(reviewImgUrl)
                .reviewDate(reviewDate.format(formatter))
                .productThumbnailUrl(orderProducts.getProductDtl().getProductMst().getProductThumbnailUrl())
                .build();
    }


}
