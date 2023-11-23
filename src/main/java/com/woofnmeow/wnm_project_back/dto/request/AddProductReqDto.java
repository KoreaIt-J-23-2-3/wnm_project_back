package com.woofnmeow.wnm_project_back.dto.request;

import com.woofnmeow.wnm_project_back.entity.ProductMst;
import lombok.Data;

@Data
public class AddProductReqDto {
    private String productName;
    private int price;
    private int petTypeId;
    private int productCategoryId;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;

    public ProductMst toEntity() {
        return ProductMst.builder()
                .productName(productName)
                .petTypeId(petTypeId)
                .productCategoryId(productCategoryId)
                .productDetailText(productDetailText)
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .build();
    }
}
