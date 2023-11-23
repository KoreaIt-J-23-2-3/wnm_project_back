package com.woofnmeow.wnm_project_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReviewByProductMstIdRespDto {
    private String profileUrl;
    private String nickname;
    private int productDtlId;
    private String reviewContent;
    private String reviewImgUrl;
    private LocalDate reviewDate;
}
