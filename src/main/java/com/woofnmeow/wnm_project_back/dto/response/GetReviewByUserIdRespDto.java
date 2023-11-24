package com.woofnmeow.wnm_project_back.dto.response;

import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReviewByUserIdRespDto {
    private String reviewContent;
    private String reviewImgUrl;
    private String reviewDate;
    private String productName;
    private String sizeName;


}
