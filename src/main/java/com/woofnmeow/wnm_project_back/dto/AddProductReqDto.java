package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddProductReqDto {
    private String productName;
    private int price;
    private int petTypeId;
    private int productCategoryId;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .price(price)
                .petTypeId(petTypeId)
                .productCategoryId(productCategoryId)
                .productDetailText(productDetailText)
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .build();
    }
}
