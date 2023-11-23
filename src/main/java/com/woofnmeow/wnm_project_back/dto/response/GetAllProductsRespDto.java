package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllProductsRespDto {
    private int productMstId;
    private String productName;
    private String petTypeId;
    private String productCategoryId;
    private String productThumbnailUrl;
    private String minPrice;
    private String maxPrice;
}
