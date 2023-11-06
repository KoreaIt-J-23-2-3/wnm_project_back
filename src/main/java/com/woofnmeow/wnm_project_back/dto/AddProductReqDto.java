package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Data;

@Data
public class AddProductReqDto {
    private String productName;
    private int productPrice;
    private String productDetailText;
    private String productThumbnail;
    private String productDetailImg;
    private int petTypeId;
    private int productCategoryId;
    private int noSize;
    private int productSizeXS;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productDetailText(productDetailText)
                .productThumbnail(productThumbnail)
                .productDetailImg(productDetailImg)
                .petTypeId(petTypeId)
                .productCategoryId(productCategoryId)
                .noSize(noSize)
                .productSizeXS(productSizeXS)
                .productSizeS(productSizeS)
                .productSizeM(productSizeM)
                .productSizeL(productSizeL)
                .productSizeXL(productSizeXL)
                .productSizeXXL(productSizeXXL)
                .build();
    }
}
