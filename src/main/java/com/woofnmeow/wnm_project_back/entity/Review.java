package com.woofnmeow.wnm_project_back.entity;

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
}
