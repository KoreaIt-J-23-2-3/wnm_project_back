package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import lombok.Data;

@Data
public class EditProductReqDto {
    private int productMstId;
    private String productName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;

    private int price;


    public ProductDtl toProductDtlEntity(int productDtlId) {
        return ProductDtl.builder()
                .productDtlId(productDtlId)
                .price(price)
                .build();
    }
}