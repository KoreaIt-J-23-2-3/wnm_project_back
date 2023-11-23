package com.woofnmeow.wnm_project_back.vo;

import com.woofnmeow.wnm_project_back.dto.response.GetAllProductsRespDto;
import lombok.Data;

@Data
public class GetAllProductsVo {
    private int productMstId;
    private String productName;
    private String petTypeId;
    private String productCategoryId;
    private String productThumbnailUrl;
    private String sizeAndPrice;

    public GetAllProductsRespDto toRespDto(String minPrice, String maxPrice) {
        GetAllProductsRespDto respDto = GetAllProductsRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .petTypeId(petTypeId)
                .productCategoryId(productCategoryId)
                .productThumbnailUrl(productThumbnailUrl)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
        return respDto;
    }
}
