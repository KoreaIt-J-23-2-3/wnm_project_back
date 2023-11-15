package com.woofnmeow.wnm_project_back.dto;

import lombok.Data;

@Data
public class EditProductReqDto {
    private int productDtlId;
    private String productName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private int petTypeId;
    private int productCategoryId;

//    public Product toProductEntity() {
//        return Product.builder()
//                .productDtlId(productDtlId)
//                .productName(productName)
//                .productDetailText(productDetailText)
//                .productThumbnailUrl(productThumbnailUrl)
//                .productDetailUrl(productDetailUrl)
//                .petTypeId(petTypeId)
//                .productCategoryId(productCategoryId)
//                .build();
//    }
}